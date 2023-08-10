package com.algorithm.algoprojectserver.controller;

import com.algorithm.algoprojectserver.dto.BoardDTO;
import com.algorithm.algoprojectserver.dto.NotificationDTO;
import com.algorithm.algoprojectserver.dto.ProblemDTO;
import com.algorithm.algoprojectserver.service.BoardService;
import com.algorithm.algoprojectserver.service.NotificationService;
import com.algorithm.algoprojectserver.service.ProblemService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class SearchController {

    ProblemService problemService;
    BoardService boardService;
    NotificationService notificationService;

    public SearchController(ProblemService problemService, BoardService boardService, NotificationService notificationService) {
        this.problemService = problemService;
        this.boardService = boardService;
        this.notificationService = notificationService;
    }

    @GetMapping("/search")
    public String search() {
        return "search";
    }


    @GetMapping("/searching")
    @ResponseBody
    public List problemSearch(@RequestParam("keyword") String keyword,
                              @RequestParam("type") String type) {

        if (type.equals("problem")) {
            List<ProblemDTO> allProblemsBySearch = problemService.getAllProblemsBySearch(keyword, 0);
            return allProblemsBySearch;
        }

        if (type.equals("board")) {
            List<BoardDTO> allBoardsBySearch = boardService.getAllBoardsBySearch(keyword, 0);
            return allBoardsBySearch;
        }

        if (type.equals("noti")) {
            List<NotificationDTO> notificationBoardBySearch = notificationService.getNotificationBoardBySearch(keyword, 0);
            return notificationBoardBySearch;
        }

        return null;
    }
}
