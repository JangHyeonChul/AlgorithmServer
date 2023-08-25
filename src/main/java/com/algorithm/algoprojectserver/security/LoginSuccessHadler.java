package com.algorithm.algoprojectserver.security;

/**
 * File Name : LoginSuccessHandler
 * Description :  관련 Mybatis 데이터베이스 Mapping
 * Update : 2023-08-21
 */



import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class LoginSuccessHadler implements AuthenticationSuccessHandler {


    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws IOException, ServletException {

        response.sendRedirect("/problem");



    }
}
