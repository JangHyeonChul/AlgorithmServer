package com.algorithm.algoprojectserver.dto;

/**
 * File Name : AlramDTO
 * Description : 게시물 알림관련 DTO
 * Update : 2023-08-18
 */

import lombok.Data;

@Data
public class AlramDTO {

    // 알림 번호
    private int a_no;

    // 게시물 번호
    private int b_no;

    // 알림 받을 유저 아이디
    private String receive_user_id;

    // 확인 여부
    private String a_check;

    AlramDTO() {}

    public AlramDTO(int b_no, String receive_user_id) {
        this.b_no = b_no;
        this.receive_user_id = receive_user_id;
    }

    public AlramDTO(int b_no, String receive_user_id, String a_check) {
        this.a_no = a_no;
        this.b_no = b_no;
        this.receive_user_id = receive_user_id;
        this.a_check = a_check;
    }
}
