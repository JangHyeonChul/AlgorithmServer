package com.algorithm.algoprojectserver.service;

/**
 * File Name : RankService
 * Description : 랭킹 관련 Service Mapper
 * Update : 2023-08-21
 */

import com.algorithm.algoprojectserver.dto.RankDTO;

import java.util.List;

public interface RankService {

    List<RankDTO> getRankInfo();
    List<RankDTO> getRankInfoHome();
    RankDTO getRankInfoByUserName(String userName);


}
