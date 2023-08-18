package com.algorithm.algoprojectserver.controller;


/**
 * File Name : SearchController
 * Description : 회원가입 관련 컨트롤러
 * Update : 2023-08-18
 */


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

    /*
     * API : /search
     * Method : GET
     * DESCRIPTION : 검색 화면 호출
     * */

    @GetMapping("/search")
    public String search() {
        return "search";
    }

    /*
     * API : /searching
     * Method : GET
     * DESCRIPTION : keyword에 해당하는 검색결과 반환
     * */

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
