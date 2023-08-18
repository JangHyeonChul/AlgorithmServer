package com.algorithm.algoprojectserver.dto;

/**
 * File Name : BoardDTO
 * Description : 게시물 정보 관련
 * Update : 2023-08-18
 */

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class BoardDTO {

    // 게시물 번호
    private int b_no;

    // 게시물 작성자
    private String user_id;

    // 게시물 제목
    private String b_title;

    // 게시물 카테고리
    private String b_category;

    // 게시물 댓글수
    private int b_comment_cnt;

    // 게시물 추천수
    private int b_recommdation;

    // 게시물 작성일
    private LocalDateTime b_create;

    // 게시물 내용
    private String b_content;

    // 게시물 질문 언어
    private String b_lang;

    // 게시물 댓글 활성화 여부
    private String b_comment_active;

    // 게시물 조회수
    private int b_viewcnt;

    // 게시물 삭제여부
    private int b_delete;

    // 게시물 작성일 변환 (ex: 3일전, 몇초전, 몇개월전)
    private String b_transCreate;



    public BoardDTO(String user_id, String b_title, String b_category, String b_content, String b_lang, String b_comment_active) {
        this.user_id = user_id;
        this.b_title = b_title;
        this.b_category = b_category;
        this.b_content = b_content;
        this.b_lang = b_lang;
        this.b_comment_active = b_comment_active;
    }

    public BoardDTO() {}

}
