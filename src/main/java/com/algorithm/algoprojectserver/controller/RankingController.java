package com.algorithm.algoprojectserver.controller;

/**
 * File Name : RankingController
 * Description : 랭킹 관련 컨트롤러
 * Update : 2023-08-18
 */

import com.algorithm.algoprojectserver.dto.RankDTO;
import com.algorithm.algoprojectserver.service.RankService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class RankingController {

    RankService rankService;

    public RankingController(RankService rankService) {
        this.rankService = rankService;
    }

    /*
     * API : /rank
     * Method : GET
     * DESCRIPTION : 랭킹 정보 호출
     * */

    @GetMapping("/rank")
    public String rank(Model model) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        List<RankDTO> rankInfos = rankService.getRankInfo();
        RankDTO userRankInfo = rankService.getRankInfoByUserName(username);


        model.addAttribute("rankInfos", rankInfos);
        model.addAttribute("userRankInfo", userRankInfo);


        return "ranking";
    }


}
