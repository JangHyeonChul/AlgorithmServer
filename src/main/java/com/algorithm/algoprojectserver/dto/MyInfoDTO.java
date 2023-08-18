package com.algorithm.algoprojectserver.dto;

/**
 * File Name : MyinfoDTO
 * Description : 마이페이지 - 정보 관련 DTO
 * Update : 2023-08-18
 */

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class MyInfoDTO {


    private int successProblem;
    private int failProblem;
    private int submitProblem;
    private int point;
    private String grade;
    private String user_role;
    private String email;
    private String create_at;

    private String user_profile_img;

}
