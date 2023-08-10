package com.algorithm.algoprojectserver.compiler;



import com.algorithm.algoprojectserver.config.CompileConstains;
import com.algorithm.algoprojectserver.dto.AnswerDTO;
import com.algorithm.algoprojectserver.service.ProblemService;
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
public class JavaCompile {

    @Autowired
    ProblemService problemService;


    public List<String> compileJavaCode(String code, Integer pageNum) {
        List<AnswerDTO> problemAnswers = problemService.getProblemAnswers(pageNum);
        List<String> compileResultList = new ArrayList<>();

        for (AnswerDTO problemAnswer : problemAnswers) {

            /*
             * 해결하고자하는 문제의 정답을 DB에서 가져와 problemAnswer에 저장한뒤 반복문으로 정답수만큼 정답체크
             * */


            JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();



            /* 1. JAVA언어(.java)로 되있는 파일을 만든다 */

            File sourceFile = new File("Main.java");
            PrintStream save = System.out;

            try (FileWriter writer = new FileWriter(sourceFile)) {
                writer.write(code);
            } catch (IOException e) {
                e.printStackTrace();
            }


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
                }

                if (!result.equals(problemAnswer.getAnswer_output())) {
                    compileResultList.add(CompileConstains.COMPILE_FAIL);
                }


            } catch (Exception e) {
                compileResultList.add(CompileConstains.COMPILE_ERROR);
                break;
            } finally {
                sourceFile.delete();
                new File("Main.java").delete();

            }
        }

        return compileResultList;
    }
}
