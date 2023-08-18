package com.algorithm.algoprojectserver.dto;

/**
 * File Name : SolveDTO
 * Description : 해결한 문제 데이터 관련 DTO
 * Update : 2023-08-18
 */

import lombok.Data;

@Data
public class SolveDTO {

    private int p_no;
    private String memberid;

    public SolveDTO() {}

}
