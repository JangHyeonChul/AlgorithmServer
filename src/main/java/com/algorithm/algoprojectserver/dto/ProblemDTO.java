package com.algorithm.algoprojectserver.dto;
import lombok.*;

/**
 * File Name : ProblemDTO
 * Description : 문제정보 관련 데이터 DTO
 * Update : 2023-08-18
 */


@Builder
@Getter
@ToString
@Setter
@AllArgsConstructor
public class ProblemDTO {

    private int p_no;
    private String p_title;
    private String p_level;
    private String p_type;
    private String p_lang;
    private String p_content;
    private int num_correct;
    private String p_hint;
    private String p_input;
    private String p_output;
    private String p_hint_active;
    private String user_id;

    public ProblemDTO() {}

}
