package com.algorithm.algoprojectserver.mapper;


import com.algorithm.algoprojectserver.dto.RankDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface RankMapper {

    List<RankDTO> selectRank();
    RankDTO selectRankByUserName(String userName);


}
