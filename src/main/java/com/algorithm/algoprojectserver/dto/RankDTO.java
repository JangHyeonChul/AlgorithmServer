package com.algorithm.algoprojectserver.dto;

/**
 * File Name : RankDTO
 * Description : 랭킹 관련 DTO
 * Update : 2023-08-18
 */

import lombok.Data;

@Data
public class RankDTO {

    private int user_rank;
    private String username;
    private String message;
    private int problem_count;
    private int point;

    public RankDTO() {}

}
