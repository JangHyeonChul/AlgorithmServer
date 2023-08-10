package com.algorithm.algoprojectserver.compiler;


import com.algorithm.algoprojectserver.config.CompileConstains;
import com.algorithm.algoprojectserver.dto.AnswerDTO;
import com.algorithm.algoprojectserver.service.ProblemService;
import org.springframework.stereotype.Component;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@Component
public class ClangCompile {

    ProblemService problemService;

    public ClangCompile(ProblemService problemService) {
        this.problemService = problemService;
    }

    public List<String> compileClangCode(String code, Integer pageNum) {
        List<AnswerDTO> problemAnswers = problemService.getProblemAnswers(pageNum);
        List<String> compileResultList = new ArrayList<>();


        for (AnswerDTO problemAnswer : problemAnswers) {


            File sourceFile = new File("world" + ".c");
            try (FileWriter writer = new FileWriter(sourceFile)) {
                writer.write(code);
            } catch (IOException e) {
                e.printStackTrace();
            }

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
                while ((line = reader.readLine()) != null) {
                    if (line.equals(problemAnswer.getAnswer_output())) {
                        compileResultList.add(CompileConstains.COMPILE_SUCCESS);
                    }

                    if (!line.equals(problemAnswer.getAnswer_output())) {
                        compileResultList.add(CompileConstains.COMPILE_FAIL);
                    }
                    System.out.println(line);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            sourceFile.delete();
            new File("world").delete();
        }

        return compileResultList;
    }
}
