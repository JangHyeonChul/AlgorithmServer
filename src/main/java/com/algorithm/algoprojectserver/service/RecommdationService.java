package com.algorithm.algoprojectserver.service;

/**
 * File Name : RecommdationService
 * Description : 추천 관련 Service Mapper
 * Update : 2023-08-21
 */

import com.algorithm.algoprojectserver.dto.RecommdationDTO;

public interface RecommdationService {

    RecommdationDTO writeBoardRecommdation(int b_no);

    RecommdationDTO getBoardRecommdation(int b_no, String username);
}
