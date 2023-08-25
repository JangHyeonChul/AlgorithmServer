package com.algorithm.algoprojectserver.serviceimpl;

/**
 * File Name : AlramServiceImpl
 * Description : 게시물 알림 관련 Service 로직
 * Update : 2023-08-21
 */

import com.algorithm.algoprojectserver.dto.AlramDTO;
import com.algorithm.algoprojectserver.dto.join.AlramBoardDTO;
import com.algorithm.algoprojectserver.mapper.AlramMapper;
import com.algorithm.algoprojectserver.mapper.BoardMapper;
import com.algorithm.algoprojectserver.service.AlramService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@Slf4j
public class AlramServiceImpl implements AlramService {

    AlramMapper alramMapper;
    BoardMapper boardMapper;

    public AlramServiceImpl(AlramMapper alramMapper, BoardMapper boardMapper) {
        this.alramMapper = alramMapper;
        this.boardMapper = boardMapper;
    }

    // 게시물 작성

    @Override
    public int writeBoardAlram(AlramDTO alramDTO, HttpServletRequest request) {


        log.info("[요청 IP : {}] 게시물 작성 요청", request.getRemoteAddr());

        return alramMapper.insertBoardAlram(alramDTO);

    }

    // 게시물 알림 업데이트

    @Override
    public void updateBoardAlram(int b_no, HttpServletRequest request) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        List<AlramDTO> alramDTOs = alramMapper.selectBoardAlram(username, b_no);

        log.info("[요청 IP : {}] {}번 게시물의 댓글 알림 갱신", request.getRemoteAddr(), request);

        if (!Objects.isNull(alramDTOs)) {

            for (AlramDTO alramDTO : alramDTOs) {
                if (alramDTO.getA_check().equals("0")) {

                    log.info("[요청 IP : {}] {}번 게시물의 알림을 확인", request.getRemoteAddr());

                    alramMapper.updateBoardAlram(username, b_no);
                }
            }
        }
    }

    // 작성자의 알림정보를 가져온다

    @Override
    public List<AlramBoardDTO> getBoardAlrams(String username) {
        return alramMapper.selectBoardAlrams(username);
    }
}
