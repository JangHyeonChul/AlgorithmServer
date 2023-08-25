package com.algorithm.algoprojectserver.service;

/**
 * File Name : AlramService
 * Description : 게시물 알람 서비스
 * Update : 2023-08-21
 */


import com.algorithm.algoprojectserver.dto.AlramDTO;
import com.algorithm.algoprojectserver.dto.join.AlramBoardDTO;
import jakarta.servlet.http.HttpServletRequest;

import java.util.List;

public interface AlramService {

    int writeBoardAlram(AlramDTO alramDTO, HttpServletRequest request);
    void updateBoardAlram(int b_no, HttpServletRequest request);
    List<AlramBoardDTO> getBoardAlrams(String username);
}
