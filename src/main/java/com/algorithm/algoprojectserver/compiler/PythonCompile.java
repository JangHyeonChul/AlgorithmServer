package com.algorithm.algoprojectserver.compiler;



import com.algorithm.algoprojectserver.config.CompileConstains;
import com.algorithm.algoprojectserver.dto.AnswerDTO;
import com.algorithm.algoprojectserver.service.ProblemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * File Name : PythonCompile
 * Description : 클라이언트에서 받은 Python언어의 알고리즘을 컴파일하고 검증
 * Update : 2023-08-11
 */


@Component
public class PythonCompile {

    @Autowired
    ProblemService problemService;


    public List<String> compilePythonCode(String code, Integer pageNum) {
        List<AnswerDTO> problemAnswers = problemService.getProblemAnswers(pageNum);
        List<String> compileResultList = new ArrayList<>();

        /*
         * 해결하고자하는 문제의 정답을 DB에서 가져와 problemAnswer에 저장한뒤 반복문으로 정답수만큼 정답체크
         * */

        for (AnswerDTO problemAnswer : problemAnswers) {
            String originalString = problemAnswer.getAnswer_input();
            StringBuilder transString = new StringBuilder();

            for (int i = 0; i < originalString.length(); i++) {
                transString.append(originalString.charAt(i));
                transString.append("\n");
            }

            String result = transString.toString();


            /* 1. Python언어(.py)로 되있는 파일을 만든다 */


            File sourceFile = new File("hello.py");
            try (FileWriter writer = new FileWriter(sourceFile)) {
                writer.write(code);
            } catch (IOException e) {
                e.printStackTrace();
            }


            /* 2. 로컬 (배포) 환경에 설치되있는 컴파일러의 기능을 통해 해당 파일 컴파일 수행 */

            String[] command = {"python", sourceFile.getPath()};
            try {
                Process process = Runtime.getRuntime().exec(command);
                OutputStream outputStream = process.getOutputStream();
                outputStream.write(result.getBytes());
                outputStream.flush();


                /* 3. 컴파일한 결과값을 대조하고 결과값을 compileResultList에 담는다 */


                BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
                String line;
                while ((line = reader.readLine()) != null) {
                    if (line.equals(problemAnswer.getAnswer_output())) {
                        compileResultList.add(CompileConstains.COMPILE_SUCCESS);
                    }

                    if (!line.equals(problemAnswer.getAnswer_output())) {
                        compileResultList.add(CompileConstains.COMPILE_FAIL);
                    }

                }

                BufferedReader errorReader = new BufferedReader(new InputStreamReader(process.getErrorStream()));
                String errorLine;
                while ((errorLine = errorReader.readLine()) != null) {
                    System.err.println(errorLine);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


        return compileResultList;
    }
}
