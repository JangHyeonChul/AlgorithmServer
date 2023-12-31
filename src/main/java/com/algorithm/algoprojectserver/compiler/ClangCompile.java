package com.algorithm.algoprojectserver.compiler;


import com.algorithm.algoprojectserver.config.CompileConstains;
import com.algorithm.algoprojectserver.dto.AnswerDTO;
import com.algorithm.algoprojectserver.service.ProblemService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * File Name : ClangCompile
 * Description : 클라이언트에서 받은 C언어의 알고리즘을 컴파일하고 검증
 * Update : 2023-08-11
 */


@Component
@Slf4j
public class ClangCompile {

    ProblemService problemService;

    public ClangCompile(ProblemService problemService) {
        this.problemService = problemService;
    }

    public List<String> compileClangCode(String code, Integer pageNum, HttpServletRequest request) {
        int answerCount = 0;
        List<AnswerDTO> problemAnswers = problemService.getProblemAnswers(pageNum);
        List<String> compileResultList = new ArrayList<>();

        log.info("[요청 IP : {}] C언어 컴파일 요청 페이지 정보 {} 번", request.getRemoteAddr(), pageNum);

        /*
        * 해결하고자하는 문제의 정답을 DB에서 가져와 problemAnswer에 저장한뒤 반복문으로 정답수만큼 정답체크
        * */

        for (AnswerDTO problemAnswer : problemAnswers) {
            answerCount++;

            log.info("[요청 IP : {}] {}번째 컴파일 요청 수행 ", request.getRemoteAddr(), answerCount);

            /* 1. C언어(.c)로 되있는 파일을 만든다 */

            log.info("[요청 IP : {}] {}번째 컴파일 요청 수행 : .c언어 파일 생성", request.getRemoteAddr(), pageNum);


            File sourceFile = new File("world" + ".c");
            try (FileWriter writer = new FileWriter(sourceFile)) {
                writer.write(code);
            } catch (IOException e) {
                e.printStackTrace();
            }

            log.info("[요청 IP : {}] {}번째 컴파일 요청 수행 : 요청한 C언어 파일 컴파일 수행", request.getRemoteAddr(), pageNum);

            /* 2. 로컬 (배포) 환경에 설치되있는 컴파일러의 기능을 통해 해당 파일 컴파일 수행 */
            String[] compileCommand = {"gcc", "-o", "world", sourceFile.getPath()};
            try {
                Process compileProcess = Runtime.getRuntime().exec(compileCommand);
                compileProcess.waitFor();
            } catch (IOException | InterruptedException e) {
                e.printStackTrace();
            }


            String[] executeCommand = {"./" + "world"};
            try {
                Process executeProcess = Runtime.getRuntime().exec(executeCommand);
                OutputStream outputStream = executeProcess.getOutputStream();
                Scanner scanner = new Scanner(problemAnswer.getAnswer_input());
                while (scanner.hasNext()) {
                    outputStream.write((scanner.nextLine() + "\n").getBytes());
                    outputStream.flush();
                }
                scanner.close();

                BufferedReader reader = new BufferedReader(new InputStreamReader(executeProcess.getInputStream()));
                String line;

                /* 3. 컴파일한 결과값을 대조하고 결과값을 compileResultList에 담는다 */

                while ((line = reader.readLine()) != null) {
                    if (line.equals(problemAnswer.getAnswer_output())) {
                        compileResultList.add(CompileConstains.COMPILE_SUCCESS);

                        log.info("[요청 IP : {}] {}번째 컴파일 요청 수행 : 컴파일 결과 : 성공", request.getRemoteAddr(), pageNum);
                    }

                    if (!line.equals(problemAnswer.getAnswer_output())) {
                        compileResultList.add(CompileConstains.COMPILE_FAIL);

                        log.info("[요청 IP : {}] {}번째 컴파일 요청 수행 : 컴파일 결과 : 실패", request.getRemoteAddr(), pageNum);
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();

                log.info("[요청 IP : {}] {}번째 컴파일 요청 수행 : 컴파일 결과 : 컴파일에러", request.getRemoteAddr(), pageNum);
            }
            sourceFile.delete();
            new File("world").delete();
        }

        return compileResultList;
    }
}
