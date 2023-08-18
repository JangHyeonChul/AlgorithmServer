package com.algorithm.algoprojectserver.dto;

/**
 * File Name : SiteDTO
 * Description : 관련 사이트 데이터 DTO
 * Update : 2023-08-18
 */

import lombok.Data;

@Data
public class SiteDTO {

    private int algo_no;
    private String algo_title;
    private String algo_link;
    private String algo_content;

    public SiteDTO() {}
}
