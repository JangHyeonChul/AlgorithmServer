package com.algorithm.algoprojectserver.service;

/**
 * File Name : PointService
 * Description : 포인트 관련 Service Mapper
 * Update : 2023-08-21
 */

public interface PointService {


    void pointServiceHandler(String result, int pageNum);
    void updateAddPoint(int point, String username);

}
