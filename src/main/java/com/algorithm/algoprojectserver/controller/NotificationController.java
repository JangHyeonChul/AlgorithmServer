package com.algorithm.algoprojectserver.controller;

/**
 * File Name : NotificationController
 * Description : 공지사항 관련 컨트롤러
 * Update : 2023-08-18
 */

import com.algorithm.algoprojectserver.PageHandler;
import com.algorithm.algoprojectserver.dto.NotificationDTO;
import com.algorithm.algoprojectserver.service.NotificationService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class NotificationController {

    NotificationService notificationService;

    public NotificationController(NotificationService notificationService) {
        this.notificationService = notificationService;
    }


    /*
     * API : /notification/{pageNum}
     * Method : GET
     * DESCRIPTION : pageNum에 해당하는 공지사항 게시물을 호출
     * */

    @GetMapping("/notification/{pageNum}")
    public String notification(@PathVariable(required = false) int pageNum,
                               @RequestParam(defaultValue = "1") int page, Model model) {

        if (pageNum == 0) {
            pageNum = notificationService.getNotificationCounts();
        }

        NotificationDTO notificationBoardByPageNum = notificationService.getNotificationBoardByPageNum(pageNum);

        int notificationCounts = notificationService.getNotificationCounts();
        PageHandler pageHandler = new PageHandler(notificationCounts, page);

        int offset = notificationService.getNotificationOffset(page, pageHandler);
        List<NotificationDTO> notificationBoard = notificationService.getNotificationBoard(offset);


        model.addAttribute("notificationBoard", notificationBoardByPageNum);
        model.addAttribute("page", page);
        model.addAttribute("offset", offset);
        model.addAttribute("notification", notificationBoard);
        model.addAttribute("ph", pageHandler);

        return "notification";
    }
}
