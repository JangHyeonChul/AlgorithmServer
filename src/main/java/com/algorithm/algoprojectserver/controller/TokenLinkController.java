package com.algorithm.algoprojectserver.controller;

/**
 * File Name : TokenLinkController
 * Description : 이메일 인증 토큰 관련 컨트롤러
 * Update : 2023-08-18
 */

import com.algorithm.algoprojectserver.config.AuthorityConstains;
import com.algorithm.algoprojectserver.dto.TokenLinkDTO;
import com.algorithm.algoprojectserver.service.TokenLinkService;
import com.algorithm.algoprojectserver.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDateTime;

@Controller
@Slf4j
public class TokenLinkController {

    TokenLinkService tokenLinkService;
    UserService userService;

    public TokenLinkController(TokenLinkService tokenLinkService, UserService userService) {
        this.tokenLinkService = tokenLinkService;
        this.userService = userService;
    }

    /*
     * API : /linkauthentication
     * Method : GET
     * DESCRIPTION : 이메일 인증 기능 수행
     * */

    @GetMapping("/linkauthentication")
    public String linkAuthentication(@RequestParam("token") String token,
                                     @RequestParam("id") String id,
                                     Model model, HttpServletRequest request) {

        log.info("[요청 IP : {}] 이메일 인증 수행 : 요청 유저의 이메일 토큰 검색", request.getRemoteAddr());

        TokenLinkDTO tokenLink = tokenLinkService.getTokenLink(id);


        LocalDateTime timeNow = LocalDateTime.now();
        LocalDateTime tokenCreateExpiry = tokenLink.getT_create_expiry();
        String roleUser = AuthorityConstains.ROLE_USER;


        boolean after = tokenCreateExpiry.isAfter(timeNow);

        if(after) {

            log.info("[요청 IP : {}] 이메일 인증 수행 : 요청 유저의 이메일 토큰 유효성 검사 통과", request.getRemoteAddr());

            log.debug("[요청 IP : {}] 이메일 인증 수행 : 요청 유저의 이메일 토큰 유효성 검사 시간대 : {}, 토큰정보 : {}, 아이디 : {}",
                    request.getRemoteAddr(), timeNow, token, id);

            log.info("[요청 IP : {}] 이메일 인증 수행 : 요청 유저의 유저권한 업데이트", request.getRemoteAddr());
            userService.updateUserAuthGrade(id, roleUser);
            model.addAttribute("welcomeMessage", "LinkSuccess");

            HttpSession session = request.getSession();
            session.invalidate();

        } else {

            log.info("[요청 IP : {}] 이메일 인증 수행 : 요청 유저의 이메일 토큰 유효성 검사 실패", request.getRemoteAddr());

            model.addAttribute("welcomeMessage", "LinkFail");

        }

        return "welcome";

    }
}
