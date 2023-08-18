package com.algorithm.algoprojectserver.dto;

/**
 * File Name : Notification
 * Description : 공지사항 관련 DTO
 * Update : 2023-08-18
 */

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class NotificationDTO {

    private int noti_no;
    private String user_id;
    private String noti_title;
    private String noti_content;
    private LocalDateTime noti_create;

    private String transNotiTime;

    NotificationDTO() {}
}
