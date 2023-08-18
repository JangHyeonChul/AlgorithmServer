package com.algorithm.algoprojectserver.mapper;

/**
 * File Name : RankMapper
 * Description : 랭킹 관련 Mybatis 데이터베이스 Mapping
 * Update : 2023-08-18
 */



import com.algorithm.algoprojectserver.dto.RankDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface RankMapper {

    List<RankDTO> selectRank();
    RankDTO selectRankByUserName(String userName);


}
