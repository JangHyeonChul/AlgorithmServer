package com.algorithm.algoprojectserver.mapper;

/**
 * File Name : RecommdationMapper
 * Description : 추천수 관련 Mybatis 데이터베이스 Mapping
 * Update : 2023-08-18
 */


import com.algorithm.algoprojectserver.dto.RecommdationDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface RecommdationMapper {

    void insertBoardRecommdation(@Param("b_no") int b_no, @Param("username") String username);
    void updateBoardRecommdationCnt(int b_no);

    RecommdationDTO selectBoardRecommdation(@Param("b_no") int b_no, @Param("username") String username);

    int getBoardRecommdationCount(int b_no);


}
