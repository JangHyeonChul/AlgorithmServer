package com.algorithm.algoprojectserver.controller;

/**
 * File Name : HelpController
 * Description : 도움말 관련 컨트롤러
 * Update : 2023-08-16
 */

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HelpController {

    /*
     * API : /help
     * Method : GET
     * DESCRIPTION : 도움말 화면 갱신
     * */

    @GetMapping("/help")
    public String help() {

        return "help";
    }

}
