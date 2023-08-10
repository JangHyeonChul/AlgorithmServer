package com.algorithm.algoprojectserver.serviceimpl;


import com.algorithm.algoprojectserver.PageHandler;
import com.algorithm.algoprojectserver.Time;
import com.algorithm.algoprojectserver.dto.NotificationDTO;
import com.algorithm.algoprojectserver.mapper.NotificationMapper;
import com.algorithm.algoprojectserver.service.NotificationService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class NotificationServiceImpl implements NotificationService {

    NotificationMapper notificationMapper;

    public NotificationServiceImpl(NotificationMapper notificationMapper) {
        this.notificationMapper = notificationMapper;
    }

    @Override
    public void writeNotification(NotificationDTO notificationDTO) {
        notificationMapper.insertNotification(notificationDTO);
    }

    @Override
    public List<NotificationDTO> getNotificationBoard(int offset) {
        List<NotificationDTO> notificationDTOS = notificationMapper.selectNotificationBoard(offset);

        for (NotificationDTO notificationDTO : notificationDTOS) {
            LocalDateTime notiCreate = notificationDTO.getNoti_create();
            String notiTime = Time.txtDate(notiCreate);
            notificationDTO.setTransNotiTime(notiTime);
        }

        return notificationDTOS;


    }

    @Override
    public List<NotificationDTO> getNotificationBoardBySearch(String keyword, int offset) {
        return notificationMapper.selectNotificationBoardBySearch(keyword, offset);
    }

    @Override
    public NotificationDTO getNotificationBoardByPageNum(int pageNum) {
        NotificationDTO notificationDTO = notificationMapper.selectNotificationBoardByPageNum(pageNum);
        LocalDateTime notiCreate = notificationDTO.getNoti_create();
        String notiTime = Time.txtDate(notiCreate);
        notificationDTO.setTransNotiTime(notiTime);

        return notificationDTO;
    }

    @Override
    public int getNotificationOffset(int page, PageHandler pageHandler) {
        return (page - 1)* pageHandler.getPageSize();
    }

    @Override
    public int getNotificationCounts() {
        return notificationMapper.countAllNotifications();
    }
}
