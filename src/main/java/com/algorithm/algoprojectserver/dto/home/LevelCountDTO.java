package com.algorithm.algoprojectserver.dto.home;

/**
 * File Name : LevelCountDTO
 * Description : 메인 화면 총문제, 초급, 중급, 상급수 데이터 DTO
 * Update : 2023-08-18
 */

import lombok.Data;

@Data
public class LevelCountDTO {

    private int totalProblemCount;
    private int easyProblemCount;
    private int normalProblemCount;
    private int hardProblemCount;

    public LevelCountDTO() {}

}
