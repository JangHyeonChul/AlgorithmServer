package com.algorithm.algoprojectserver.service;


import com.algorithm.algoprojectserver.dto.AlramDTO;
import com.algorithm.algoprojectserver.dto.join.AlramBoardDTO;

import java.util.List;

public interface AlramService {

    int writeBoardAlram(AlramDTO alramDTO);
    void updateBoardAlram(int b_no);
    List<AlramBoardDTO> getBoardAlrams(String username);
}
