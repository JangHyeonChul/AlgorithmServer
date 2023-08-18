package com.algorithm.algoprojectserver.mapper;

/**
 * File Name : AlramMapper
 * Description : 알람 관련 Mybatis 데이터베이스 Mapping
 * Update : 2023-08-18
 */

import com.algorithm.algoprojectserver.dto.AlramDTO;
import com.algorithm.algoprojectserver.dto.join.AlramBoardDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface AlramMapper {

    int insertBoardAlram(AlramDTO alramDTO);
    int updateBoardAlram(@Param("username") String username, @Param("b_no") int b_no);

    List<AlramDTO> selectBoardAlram(@Param("username") String username, @Param("b_no") int b_no);
    List<AlramBoardDTO> selectBoardAlrams(String username);
}
