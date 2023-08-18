package com.algorithm.algoprojectserver.dto.join;

/**
 * File Name : AlramBoardDTO
 * Description : 게시물 알림 데이터 관련 DTO
 * Update : 2023-08-18
 */


import lombok.Data;

@Data
public class AlramBoardDTO {

    // 알람 번호
    private int a_no;

    // 게시물 번호
    private int b_no;

    // 받을 유저 아이디
    private String receive_user_id;

    // 체크 여부
    private String a_check;

    // 게시물 제목
    private String b_title;

    // 게시물 카테고리
    private String b_category;

    AlramBoardDTO() {}
}
