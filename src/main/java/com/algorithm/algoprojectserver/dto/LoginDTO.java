package com.algorithm.algoprojectserver.dto;


/**
 * File Name : LoginDTO
 * Description : 로그인 정보 데이터 관련 DTO
 * Update : 2023-08-18
 */

import lombok.Data;

@Data
public class LoginDTO {

    private String username;
    private String password;
    private boolean idsave;

    public LoginDTO() {}

}
