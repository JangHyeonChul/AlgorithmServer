package com.algorithm.algoprojectserver.dto.home;

import lombok.Data;

@Data
public class LevelCountDTO {

    private int totalProblemCount;
    private int easyProblemCount;
    private int normalProblemCount;
    private int hardProblemCount;

    public LevelCountDTO() {}

}
