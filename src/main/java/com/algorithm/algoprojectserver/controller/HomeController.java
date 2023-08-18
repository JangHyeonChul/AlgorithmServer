package com.algorithm.algoprojectserver.controller;

/**
 * File Name : HomeController
 * Description : 메인화면 관련 컨트롤러
 * Update : 2023-08-16
 */


import com.algorithm.algoprojectserver.dto.*;
import com.algorithm.algoprojectserver.dto.home.LevelCountDTO;
import com.algorithm.algoprojectserver.service.*;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
@Slf4j
public class HomeController {

    HomeService homeService;
    RankService rankService;
    NotificationService notificationService;
    BoardService boardService;
    ProblemService problemService;
    UserService userService;

    public HomeController(HomeService homeService, RankService rankService, NotificationService notificationService, BoardService boardService, ProblemService problemService, UserService userService) {
        this.homeService = homeService;
        this.rankService = rankService;
        this.notificationService = notificationService;
        this.boardService = boardService;
        this.problemService = problemService;
        this.userService = userService;
    }

    /*
     * API : /
     * Method : GET
     * DESCRIPTION : 메인 화면 갱신 총문제수, 랭킹, 공지사항, 게시물, 문제가 표기됨
     * */

    @GetMapping("/")
    public String home(Model model, HttpServletRequest request) {

        LevelCountDTO homeLevelCount = homeService.getHomeLevelCount();

        log.info("[요청 IP : {}] 메인 화면 접속", request.getRemoteAddr());

        List<RankDTO> rankInfoHome = rankService.getRankInfoHome();
        List<NotificationDTO> notificationBoard = notificationService.getNotificationBoard(0);
        List<BoardDTO> totalBoard = boardService.getAllBoards(0, null);
        List<ProblemDTO> allProblems = problemService.getAllProblems(0);

        model.addAttribute("levelCount", homeLevelCount);
        model.addAttribute("rankInfos", rankInfoHome);
        model.addAttribute("notifications", notificationBoard);
        model.addAttribute("boards", totalBoard);
        model.addAttribute("problems", allProblems);


        return "main";
    }
}
