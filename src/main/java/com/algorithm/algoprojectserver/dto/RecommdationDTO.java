package com.algorithm.algoprojectserver.dto;


/**
 * File Name : RecommdationDTO
 * Description : 추천수 관련 데이터 DTO
 * Update : 2023-08-18
 */

import lombok.Data;

@Data
public class RecommdationDTO {

    private int recom_no;
    private String user_id;
    private int b_no;
    private boolean insertCheck;
    private int recommdationCnt;

    RecommdationDTO() {}

}
