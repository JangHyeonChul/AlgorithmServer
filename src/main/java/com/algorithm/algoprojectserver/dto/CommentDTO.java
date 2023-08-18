package com.algorithm.algoprojectserver.dto;

/**
 * File Name : CommentDTO
 * Description : 댓글 관련 데이터 DTO
 * Update : 2023-08-18
 */

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class CommentDTO {

    // 댓글 번호
    private int c_no;

    // 게시물 번호
    private int b_no;

    // 작성자
    private String user_id;

    // 댓글내용
    private String c_comment;

    // 작성일
    private LocalDateTime c_create;

    // 댓글추천수
    private int c_recommadation;

    private String c_transCreate;
    private boolean showModify;

    public CommentDTO() {}

    public CommentDTO(int b_no, String user_id, String c_comment) {
        this.b_no = b_no;
        this.user_id = user_id;
        this.c_comment = c_comment;
    }
}
