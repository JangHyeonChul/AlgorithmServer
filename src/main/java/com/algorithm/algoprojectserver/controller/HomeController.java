package com.algorithm.algoprojectserver.controller;

import com.algorithm.algoprojectserver.dto.*;
import com.algorithm.algoprojectserver.dto.home.LevelCountDTO;
import com.algorithm.algoprojectserver.service.*;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
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

    @GetMapping("/")
    public String home(Model model) {

        LevelCountDTO homeLevelCount = homeService.getHomeLevelCount();
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
