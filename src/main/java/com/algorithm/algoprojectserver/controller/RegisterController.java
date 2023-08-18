package com.algorithm.algoprojectserver.controller;

/**
 * File Name : RegisterController
 * Description : 회원가입 관련 컨트롤러
 * Update : 2023-08-18
 */



import com.algorithm.algoprojectserver.dto.MemberDTO;
import com.algorithm.algoprojectserver.service.TokenLinkService;
import com.algorithm.algoprojectserver.service.UserService;
import com.algorithm.algoprojectserver.validator.RegisterValidaor;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@Slf4j
public class RegisterController {

    UserService userService;
    RegisterValidaor registerValidaor;
    TokenLinkService tokenLinkService;

    public RegisterController(UserService userService, RegisterValidaor registerValidaor, TokenLinkService tokenLinkService) {
        this.userService = userService;
        this.registerValidaor = registerValidaor;
        this.tokenLinkService = tokenLinkService;
    }

    /*
     * API : /register
     * Method : GET
     * DESCRIPTION : 회원가입 화면 호출
     * */

    @GetMapping("/register")
    public String register(Model model) {
        model.addAttribute("memberDTO", new MemberDTO());

        return "register";
    }

    /*
     * API : /welcome
     * Method : GET
     * DESCRIPTION : 회원가입 성공 화면 호출
     * */

    @GetMapping("/welcome")
    public String welcome() {

        return "welcome";
    }

    /*
     * API : /register
     * Method : POST
     * DESCRIPTION : 회원가입 기능 수행
     * */

    @PostMapping("/register")
    public String signup(@ModelAttribute MemberDTO memberDTO, BindingResult bindingResult, RedirectAttributes redirectAttributes,
                         HttpServletRequest request) {

        log.debug("[요청 IP : {}] 회원 가입 수행 : 유효성 검사 진행", request.getRemoteAddr());

        registerValidaor.validate(memberDTO, bindingResult);

        if(bindingResult.hasErrors()) {
            return "register";
        }

        log.debug("[요청 IP : {}] 회원 가입 수행 : 유효성 검사 통과", request.getRemoteAddr());

        userService.registerUser(memberDTO);

        log.debug("[요청 IP : {}] 회원 가입 수행 : 회원 가입 완료", request.getRemoteAddr());

        redirectAttributes.addFlashAttribute("welcomeMessage", "RegisterSuccess");

        return "redirect:/welcome";
    }
}
