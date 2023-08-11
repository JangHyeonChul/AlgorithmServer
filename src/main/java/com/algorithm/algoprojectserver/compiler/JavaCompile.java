package com.algorithm.algoprojectserver.compiler;



import com.algorithm.algoprojectserver.config.CompileConstains;
import com.algorithm.algoprojectserver.dto.AnswerDTO;
import com.algorithm.algoprojectserver.service.ProblemService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.tools.JavaCompiler;
import javax.tools.ToolProvider;
import java.io.*;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.List;

/**
 * File Name : JavaCompile
 * Description : 클라이언트에서 받은 JAVA언어의 알고리즘을 컴파일하고 검증
 * Update : 2023-08-11
 */


@Component
@Slf4j
public class JavaCompile {

    @Autowired
    ProblemService problemService;


    public List<String> compileJavaCode(String code, Integer pageNum, HttpServletRequest request) {
        int answerCount = 0;
        List<AnswerDTO> problemAnswers = problemService.getProblemAnswers(pageNum);
        List<String> compileResultList = new ArrayList<>();

        for (AnswerDTO problemAnswer : problemAnswers) {
            answerCount++;

            log.info("[요청 IP : {}] JAVA언어 컴파일 요청 페이지 정보 {} 번", request.getRemoteAddr(), pageNum);

            log.info("[요청 IP : {}] {}번째 컴파일 요청 수행 ", request.getRemoteAddr(), answerCount);

            /*
             * 해결하고자하는 문제의 정답을 DB에서 가져와 problemAnswer에 저장한뒤 반복문으로 정답수만큼 정답체크
             * */


            JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();


            /* 1. JAVA언어(.java)로 되있는 파일을 만든다 */

            log.info("[요청 IP : {}] {}번째 컴파일 요청 수행 : .java언어 파일 생성", request.getRemoteAddr(), pageNum);

            File sourceFile = new File("Main.java");
            PrintStream save = System.out;

            try (FileWriter writer = new FileWriter(sourceFile)) {
                writer.write(code);
            } catch (IOException e) {
                e.printStackTrace();
            }


            log.info("[요청 IP : {}] {}번째 컴파일 요청 수행 : 요청한 JAVA언어 파일 컴파일 수행", request.getRemoteAddr(), pageNum);

            /* 2. 로컬 (배포) 환경에 설치되있는 컴파일러의 기능을 통해 해당 파일 컴파일 수행 */

            int run = compiler.run(null, null, null, sourceFile.getPath());

            System.setIn(new ByteArrayInputStream(problemAnswer.getAnswer_input().getBytes()));


            try (URLClassLoader classLoader = URLClassLoader.newInstance(new URL[]{new File("").toURI().toURL()})) {
                if (run != 0) {
                    throw new Exception("Compile Error!");
                }

                Class<?> userClass = classLoader.loadClass("Main");


                Object instance = userClass.getDeclaredConstructor().newInstance();


                Method runMethod = userClass.getMethod("main");


                ByteArrayOutputStream out = new ByteArrayOutputStream();
                PrintStream printStream = new PrintStream(out);
                System.setOut(printStream);


                runMethod.invoke(instance);


                System.setOut(save);
                printStream.close();


                String result = out.toString().trim();


                /* 3. 컴파일한 결과값을 대조하고 결과값을 compileResultList에 담는다 */

                if (result.equals(problemAnswer.getAnswer_output())) {
                    compileResultList.add(CompileConstains.COMPILE_SUCCESS);

                    log.info("[요청 IP : {}] {}번째 컴파일 요청 수행 : 컴파일 결과 : 성공", request.getRemoteAddr(), pageNum);

                }

                if (!result.equals(problemAnswer.getAnswer_output())) {
                    compileResultList.add(CompileConstains.COMPILE_FAIL);

                    log.info("[요청 IP : {}] {}번째 컴파일 요청 수행 : 컴파일 결과 : 실패", request.getRemoteAddr(), pageNum);

                }


            } catch (Exception e) {
                compileResultList.add(CompileConstains.COMPILE_ERROR);

                log.info("[요청 IP : {}] {}번째 컴파일 요청 수행 : 컴파일 결과 : 컴파일에러", request.getRemoteAddr(), pageNum);

                break;
            } finally {
                sourceFile.delete();
                new File("Main.java").delete();

            }
        }

        return compileResultList;
    }
}
