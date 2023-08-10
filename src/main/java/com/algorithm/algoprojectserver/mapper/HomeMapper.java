package com.algorithm.algoprojectserver.mapper;

import com.algorithm.algoprojectserver.dto.home.LevelCountDTO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface HomeMapper {

    LevelCountDTO selectHomeLevel();
}
