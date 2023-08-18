package com.algorithm.algoprojectserver.mapper;

/**
 * File Name : NotificationMapper
 * Description : 공지사항 관련 Mybatis 데이터베이스 Mapping
 * Update : 2023-08-18
 */



import com.algorithm.algoprojectserver.dto.NotificationDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface NotificationMapper {

    void insertNotification(NotificationDTO notificationDTO);

    List<NotificationDTO> selectNotificationBoard(int offset);
    List<NotificationDTO> selectNotificationBoardBySearch(@Param("keyword") String keyword, @Param("offset") int offset);
    NotificationDTO selectNotificationBoardByPageNum(int pageNum);

    int countAllNotifications();


}
