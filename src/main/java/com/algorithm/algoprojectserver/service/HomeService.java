package com.algorithm.algoprojectserver.service;


/**
 * File Name : HomeService
 * Description : 메인화면 관련 Service Mapper
 * Update : 2023-08-21
 */

import com.algorithm.algoprojectserver.dto.home.LevelCountDTO;

public interface HomeService {

    LevelCountDTO getHomeLevelCount();


}
