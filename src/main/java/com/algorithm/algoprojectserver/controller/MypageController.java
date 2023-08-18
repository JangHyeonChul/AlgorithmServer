package com.algorithm.algoprojectserver.controller;

/**
 * File Name : Mypagecontroller
 * Description : 로그인 관련 컨트롤러
 * Update : 2023-08-18
 */


import com.algorithm.algoprojectserver.dto.BoardDTO;
import com.algorithm.algoprojectserver.dto.MemberDTO;
import com.algorithm.algoprojectserver.dto.MyInfoDTO;
import com.algorithm.algoprojectserver.dto.join.AlramBoardDTO;
import com.algorithm.algoprojectserver.service.AlramService;
import com.algorithm.algoprojectserver.service.BoardService;
import com.algorithm.algoprojectserver.service.TokenLinkService;
import com.algorithm.algoprojectserver.service.UserService;
import com.algorithm.algoprojectserver.validator.MypagePasswordValidator;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/mypage")
public class MypageController {

    UserService userService;
    MypagePasswordValidator mypagePasswordValidator;
    TokenLinkService tokenLinkService;
    BoardService boardService;
    AlramService alramService;

    public MypageController(UserService userService,
                            MypagePasswordValidator mypagePasswordValidator,
                            TokenLinkService tokenLinkService,
                            BoardService boardService,
                            AlramService alramService) {
        this.userService = userService;
        this.mypagePasswordValidator = mypagePasswordValidator;
        this.tokenLinkService = tokenLinkService;
        this.boardService = boardService;
        this.alramService = alramService;
    }

    /*
     * API : /mypage
     * Method : GET
     * DESCRIPTION : 마이페이지 화면 호출
     * */

    @GetMapping("")
    public String mypage(Model model) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        MyInfoDTO userInfo = userService.getUserInfo(username);

        model.addAttribute("userInfo", userInfo);

        return "mypage";
    }

    /*
     * API : /modifyinfo
     * Method : POST
     * DESCRIPTION : 마이페이지 - 정보변경 로그인된 유저의 정보를 제공하는 API
     * */

    @PostMapping("/modifyinfo")
    @ResponseBody
    public String modifyInfo(@RequestParam("username") String username,
                           @RequestParam("message") String message) {

        String user = SecurityContextHolder.getContext().getAuthentication().getName();

        return userService.userModifyInfoValidCheck(username, user, message);
    }

    /*
     * API : /modifyinfo
     * Method : POST
     * DESCRIPTION : 마이페이지 - 정보변경 로그인된 유저의 정보 화면을 제공하는 API
     * */

    @GetMapping("/modifyinfo")
    @ResponseBody
    public MemberDTO modifyInfoBtn() {
        String user = SecurityContextHolder.getContext().getAuthentication().getName();

        return userService.findByUserIdOrEmail(user);
    }

    /*
     * API : /password
     * Method : POST
     * DESCRIPTION : 마이페이지 - 비밀번호 변경 로그인된 유저의 비밀번호 변경기능을 제공하는 API
     * */

    @PostMapping("/password")
    @ResponseBody
    public Map<String, String> passwordChange(@RequestParam("originalPwd") String originalPwd,
                               @RequestParam("newPassword") String newPassword,
                               @RequestParam("newPasswordCheck") String newPasswordCheck) {

        return mypagePasswordValidator.myPagePasswordVaild(originalPwd, newPassword, newPasswordCheck);
    }

    /*
     * API : /email
     * Method : POST
     * DESCRIPTION : 마이페이지 - 이메일 변경 로그인된 유저의 이메일 변경기능을 제공하는 API
     * */

    @PostMapping("/email")
    @ResponseBody
    public Map<String, String> emailSubmit() {

        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        tokenLinkService.tokenLinkHandler(username);


        return tokenLinkService.getTokenData(username);


    }

    /*
     * API : /email
     * Method : GET
     * DESCRIPTION : 마이페이지 - 이메일 변경 로그인된 유저의 이메일 변경기능을 제공하는 API
     * */

    @GetMapping("/email")
    @ResponseBody
    public Map<String, String> emailSubmitBtn() {

        String username = SecurityContextHolder.getContext().getAuthentication().getName();


        return tokenLinkService.getTokenData(username);

    }

    /*
     * API : /board
     * Method : GET
     * DESCRIPTION : 마이페이지 - 내가쓴게시물 로그인된 유저의 내가쓴게시물을 호출하는 API
     * */

    @GetMapping("/board")
    @ResponseBody
    public List<BoardDTO> boardSubmitBtn() {

        String username = SecurityContextHolder.getContext().getAuthentication().getName();


        return boardService.getAllBoardsByUser(username);
    }

    /*
     * API : /alram
     * Method : GET
     * DESCRIPTION : 마이페이지 - 알림 로그인된 사용자의 게시물에 댓글이 달릴경우 알림정보를 호출하는 API
     * */

    @GetMapping("/alram")
    @ResponseBody
    public List<AlramBoardDTO> alramSubmitBtn() {

        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        List<AlramBoardDTO> boardAlrams = alramService.getBoardAlrams(username);

        return boardAlrams;
    }

    /*
     * API : /userinfo
     * Method : GET
     * DESCRIPTION : 마이페이지 - 정보 로그인된 사용자의 정보를 불러오는 API
     * */


    @GetMapping("/userinfo")
    @ResponseBody
    public MyInfoDTO userInfoSubmitBtn() {

        String username = SecurityContextHolder.getContext().getAuthentication().getName();

        return userService.getUserInfo(username);

    }



}
