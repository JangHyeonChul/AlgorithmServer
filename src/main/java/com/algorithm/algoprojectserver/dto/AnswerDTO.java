package com.algorithm.algoprojectserver.dto;

/**
 * File Name : Answer
 * Description : 알고리즘 테스트 케이스 입력값, 출력값 데이터 DTO
 * Update : 2023-08-18
 */

import lombok.Data;

@Data
public class AnswerDTO {

    // 문제 번호
    private int p_no;

    // 입력값
    private String answer_input;

    // 출력값
    private String answer_output;

    public AnswerDTO() {}

}
