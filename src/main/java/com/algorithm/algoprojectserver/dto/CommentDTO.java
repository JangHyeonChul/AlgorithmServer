package com.algorithm.algoprojectserver.dto;


import lombok.Data;

import java.time.LocalDateTime;

@Data
public class CommentDTO {

    private int c_no;
    private int b_no;
    private String user_id;
    private String c_comment;
    private LocalDateTime c_create;
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
