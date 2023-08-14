package com.algorithm.algoprojectserver.compiler;



import com.algorithm.algoprojectserver.config.CompileConstains;
import com.algorithm.algoprojectserver.dto.AnswerDTO;
import com.algorithm.algoprojectserver.service.ProblemService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import javax.tools.JavaCompiler;
import javax.tools.ToolProvider;
import java.io.*;
import java.lang.reflect.Method;
import java.net.URI;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.List;

/**
 * File Name : JavaCompile
 * Description : 클라이언트에서 받은 JAVA언어의 알고리즘을 컴파일하고 검증
 * Update : 2023-08-14
 */

/*
* 클라이언트에서 받은 사용자의 코드를 동적 컴파일 하기위해 따로 마련된 Java언어 컴파일 서버를 시켜야함
* http://localhost:8081
* */


@Component
@Slf4j
public class JavaCompile {

    private final String COMPILE_SERVER_URL = "http://localhost:8081";

    @Autowired
    ProblemService problemService;


    public List<String> compileJavaCode(String code, Integer pageNum, HttpServletRequest servletRequest) {

        /*
        * 해당 페이지의 정답값을 불러온다음 AnswerDTO에 담는다
        * */


        List<AnswerDTO> problemAnswers = problemService.getProblemAnswers(pageNum);
        List<String> compileResultList = new ArrayList<>();

        int answerCount = 0;

        for (AnswerDTO problemAnswer : problemAnswers) {
            answerCount++;
            // 컴파일 서버와 통신하기 위한 RestTemplate 객체 생성

            RestTemplate restTemplate = new RestTemplate();

            /*
            * 각각 input에는 알고리즘 검증을 위한 정답값, output에는 결과값이 들어가 시도횟수만큼 테스트를 돌린다
            * */

            URI uri = UriComponentsBuilder
                    .fromUriString(COMPILE_SERVER_URL)
                    .path("/")
                    .queryParam("input", problemAnswer.getAnswer_input())
                    .queryParam("output", problemAnswer.getAnswer_output())
                    .encode().build().toUri();

            RequestEntity<String> request = RequestEntity
                    .post(uri)
                    .body(code);

            ResponseEntity<String> responseEntity = restTemplate.exchange(request, String.class);
            String body = responseEntity.getBody();

            log.debug("[요청 IP : {}] {}번째 컴파일 요청 수행 : 입력 코드 : {}", servletRequest.getRemoteAddr(), answerCount, code);


                if (body.equals("Success")) {
                    compileResultList.add(CompileConstains.COMPILE_SUCCESS);

                    log.info("[요청 IP : {}] {}번째 컴파일 요청 수행 : 컴파일 결과 : 성공", servletRequest.getRemoteAddr(), answerCount);

                }

            if (body.equals("Fail")) {
                    compileResultList.add(CompileConstains.COMPILE_FAIL);

                    log.info("[요청 IP : {}] {}번째 컴파일 요청 수행 : 컴파일 결과 : 실패", servletRequest.getRemoteAddr(), answerCount);

                }

            if (body.equals("Error") || body.equals("알수없는 오류")) {
                compileResultList.add(CompileConstains.COMPILE_ERROR);

                log.info("[요청 IP : {}] {}번째 컴파일 요청 수행 : 컴파일 결과 : 컴파일 에러", servletRequest.getRemoteAddr(), answerCount);

            }
        }

        return compileResultList;
    }
}
