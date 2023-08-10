package com.algorithm.algoprojectserver.serviceimpl;


import com.algorithm.algoprojectserver.dto.AlramDTO;
import com.algorithm.algoprojectserver.dto.join.AlramBoardDTO;
import com.algorithm.algoprojectserver.mapper.AlramMapper;
import com.algorithm.algoprojectserver.mapper.BoardMapper;
import com.algorithm.algoprojectserver.service.AlramService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class AlramServiceImpl implements AlramService {

    AlramMapper alramMapper;
    BoardMapper boardMapper;

    public AlramServiceImpl(AlramMapper alramMapper, BoardMapper boardMapper) {
        this.alramMapper = alramMapper;
        this.boardMapper = boardMapper;
    }

    @Override
    public int writeBoardAlram(AlramDTO alramDTO) {
        return alramMapper.insertBoardAlram(alramDTO);
    }

    @Override
    public void updateBoardAlram(int b_no) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        List<AlramDTO> alramDTOs = alramMapper.selectBoardAlram(username, b_no);

        if (!Objects.isNull(alramDTOs)) {
            for (AlramDTO alramDTO : alramDTOs) {
                if (alramDTO.getA_check().equals("0")) {
                    alramMapper.updateBoardAlram(username, b_no);
                }
            }
        }
    }

    @Override
    public List<AlramBoardDTO> getBoardAlrams(String username) {
        return alramMapper.selectBoardAlrams(username);
    }
}
