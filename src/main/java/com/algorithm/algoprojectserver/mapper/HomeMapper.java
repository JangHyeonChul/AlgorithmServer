package com.algorithm.algoprojectserver.mapper;

/**
 * File Name : HomeMapper
 * Description : 메인홈 관련 Mybatis 데이터베이스 Mapping
 * Update : 2023-08-18
 */


import com.algorithm.algoprojectserver.dto.home.LevelCountDTO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface HomeMapper {

    LevelCountDTO selectHomeLevel();
}
