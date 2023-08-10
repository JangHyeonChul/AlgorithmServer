package com.algorithm.algoprojectserver.dto;

import lombok.Data;

@Data
public class AlramDTO {

    private int a_no;
    private int b_no;
    private String receive_user_id;
    private String a_check;

    AlramDTO() {}

    public AlramDTO(int b_no, String receive_user_id) {
        this.b_no = b_no;
        this.receive_user_id = receive_user_id;
    }

    public AlramDTO(int b_no, String receive_user_id, String a_check) {
        this.a_no = a_no;
        this.b_no = b_no;
        this.receive_user_id = receive_user_id;
        this.a_check = a_check;
    }
}
