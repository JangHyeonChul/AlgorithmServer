package com.algorithm.algoprojectserver.controller;

/**
 * File Name : LoginController
 * Description : 로그인 관련 컨트롤러
 * Update : 2023-08-16
 */


import com.algorithm.algoprojectserver.dto.LoginDTO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class LoginController {

    /*
     * API : /login
     * Method : GET
     * DESCRIPTION : 로그인 화면 갱신
     * */

    @GetMapping("/login")
    public String login(Model model) {

        model.addAttribute("loginDTO", new LoginDTO());

        return "login";
    }

    /*
     * API : /logout
     * Method : GET
     * DESCRIPTION : 로그아웃 화면 갱신
     * */

    @GetMapping("/logout")
    public String logout(HttpServletRequest request) {
        SecurityContextHolder.clearContext();

        HttpSession session = request.getSession(false);

        if (session != null) {
            session.removeAttribute("SPRING_SECURITY_CONTEXT");
        }

        return "/problem/problem";
    }
}
