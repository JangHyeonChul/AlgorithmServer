package com.algorithm.algoprojectserver.service;


import com.algorithm.algoprojectserver.dto.RecommdationDTO;

public interface RecommdationService {

    RecommdationDTO writeBoardRecommdation(int b_no);

    RecommdationDTO getBoardRecommdation(int b_no, String username);
}
