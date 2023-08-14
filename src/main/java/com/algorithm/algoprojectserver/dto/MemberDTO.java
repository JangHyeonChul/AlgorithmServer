package com.algorithm.algoprojectserver.dto;


import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class MemberDTO {

    private String user_id;
    private String user_email;
    private String user_nickname;
    private String user_password;
    private String user_confirmPassword;
    private String user_message;
    private int user_point;
    private String user_role;
    private String user_grade;
    private LocalDateTime create_at;

    private String user_profile_img;

    public MemberDTO() {}


}
