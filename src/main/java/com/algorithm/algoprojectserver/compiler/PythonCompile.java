package com.algorithm.algoprojectserver.compiler;



import com.algorithm.algoprojectserver.config.CompileConstains;
import com.algorithm.algoprojectserver.dto.AnswerDTO;
import com.algorithm.algoprojectserver.service.ProblemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

@Component
public class PythonCompile {

    @Autowired
    ProblemService problemService;


    public List<String> compilePythonCode(String code, Integer pageNum) {
        List<AnswerDTO> problemAnswers = problemService.getProblemAnswers(pageNum);
        List<String> compileResultList = new ArrayList<>();


        for (AnswerDTO problemAnswer : problemAnswers) {
            String originalString = problemAnswer.getAnswer_input();
            StringBuilder transString = new StringBuilder();

            for (int i = 0; i < originalString.length(); i++) {
                transString.append(originalString.charAt(i));
                transString.append("\n");
            }

            String result = transString.toString();


            File sourceFile = new File("hello.py");
            try (FileWriter writer = new FileWriter(sourceFile)) {
                writer.write(code);
            } catch (IOException e) {
                e.printStackTrace();
            }

            String[] command = {"python", sourceFile.getPath()};
            try {
                Process process = Runtime.getRuntime().exec(command);
                OutputStream outputStream = process.getOutputStream();
                outputStream.write(result.getBytes());
                outputStream.flush();

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
