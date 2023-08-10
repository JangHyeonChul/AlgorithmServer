package com.algorithm.algoprojectserver.serviceimpl;


import com.algorithm.algoprojectserver.dto.home.LevelCountDTO;
import com.algorithm.algoprojectserver.mapper.HomeMapper;
import com.algorithm.algoprojectserver.service.HomeService;
import com.algorithm.algoprojectserver.service.RankService;
import org.springframework.stereotype.Service;

@Service
public class HomeServiceImpl implements HomeService {

    HomeMapper homeMapper;
    RankService rankService;

    public HomeServiceImpl(HomeMapper homeMapper, RankService rankService) {
        this.homeMapper = homeMapper;
        this.rankService = rankService;
    }

    @Override
    public LevelCountDTO getHomeLevelCount() {
        return homeMapper.selectHomeLevel();
    }

}
