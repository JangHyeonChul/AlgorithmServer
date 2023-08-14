package com.algorithm.algoprojectserver.controller;

import com.algorithm.algoprojectserver.config.AuthorityConstains;
import com.algorithm.algoprojectserver.dto.TokenLinkDTO;
import com.algorithm.algoprojectserver.service.TokenLinkService;
import com.algorithm.algoprojectserver.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDateTime;

@Controller
public class TokenLinkController {

    TokenLinkService tokenLinkService;
    UserService userService;

    public TokenLinkController(TokenLinkService tokenLinkService, UserService userService) {
        this.tokenLinkService = tokenLinkService;
        this.userService = userService;
    }

    @GetMapping("/linkauthentication")
    public String linkAuthentication(@RequestParam("token") String token,
                                     @RequestParam("id") String id,
                                     Model model, HttpServletRequest request) {

        TokenLinkDTO tokenLink = tokenLinkService.getTokenLink(id);
        LocalDateTime timeNow = LocalDateTime.now();
        LocalDateTime tokenCreateExpiry = tokenLink.getT_create_expiry();
        String roleUser = AuthorityConstains.ROLE_USER;


        boolean after = tokenCreateExpiry.isAfter(timeNow);

        if(after) {
            userService.updateUserAuthGrade(id, roleUser);
            model.addAttribute("welcomeMessage", "LinkSuccess");

            HttpSession session = request.getSession();
            session.invalidate();

        } else {
            model.addAttribute("welcomeMessage", "LinkFail");

        }

        return "welcome";

    }
}
