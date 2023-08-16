package com.algorithm.algoprojectserver.controller.admin;

/**
 * File Name : AdminNotificationController
 * Description : 어드민 공지사항 관련 컨트롤러
 * Update : 2023-08-15
 */


import com.algorithm.algoprojectserver.dto.NotificationDTO;
import com.algorithm.algoprojectserver.service.NotificationService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Slf4j
@RequestMapping("/admin")
public class AdminNotificationController {

    NotificationService notificationService;

    public AdminNotificationController(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    /*
     * API : /admin/notification
     * Method : GET
     * DESCRIPTION : 어드민 권한을 가진 아이디의 공지사항 등록창으로 이동
     * */

    @GetMapping("/notification")
    public String notification(@ModelAttribute("notificationDTO") NotificationDTO notificationDTO) {

        return "/admin/admin-notification";
    }

    /*
     * API : /admin/notification
     * Method : POST
     * DESCRIPTION : 어드민 권한을 가진 아이디의 공지사항 등록을 수행
     * */

    @PostMapping("/notification")
    public String notificationSubmit(NotificationDTO notificationDTO, HttpServletRequest request) {

        String username = SecurityContextHolder.getContext().getAuthentication().getName();

        log.info("[요청 IP : {}] {} 아이디의 공지사항 등록 요청", request.getRemoteAddr(), username);

        notificationDTO.setUser_id(username);
        notificationService.writeNotification(notificationDTO);


        return "redirect:/notification/0";
    }



}
