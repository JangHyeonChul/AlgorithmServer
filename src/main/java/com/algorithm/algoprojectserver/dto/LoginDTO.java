package com.algorithm.algoprojectserver.dto;

import lombok.Data;

@Data
public class LoginDTO {

    private String username;
    private String password;
    private boolean idsave;

    public LoginDTO() {}

}
