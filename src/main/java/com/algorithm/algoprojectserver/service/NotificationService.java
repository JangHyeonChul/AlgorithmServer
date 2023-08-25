package com.algorithm.algoprojectserver.service;

/**
 * File Name : NotificationService
 * Description : 공지사항 관련 Service Mapper
 * Update : 2023-08-21
 */

import com.algorithm.algoprojectserver.PageHandler;
import com.algorithm.algoprojectserver.dto.NotificationDTO;

import java.util.List;

public interface NotificationService {

    void writeNotification(NotificationDTO notificationDTO);

    List<NotificationDTO> getNotificationBoard(int offset);
    List<NotificationDTO> getNotificationBoardBySearch(String keyword, int offset);
    NotificationDTO getNotificationBoardByPageNum(int pageNum);

    int getNotificationCounts();
    int getNotificationOffset(int page, PageHandler pageHandler);
}
