package com.algorithm.algoprojectserver.service;


import com.algorithm.algoprojectserver.dto.RankDTO;

import java.util.List;

public interface RankService {

    List<RankDTO> getRankInfo();
    List<RankDTO> getRankInfoHome();
    RankDTO getRankInfoByUserName(String userName);


}
