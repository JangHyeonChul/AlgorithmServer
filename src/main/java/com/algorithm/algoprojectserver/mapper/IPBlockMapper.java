package com.algorithm.algoprojectserver.mapper;

/**
 * File Name : IpBlockMapper
 * Description : 아이피 차단 관련 Mybatis 데이터베이스 Mapping
 * Update : 2023-08-18
 */


public interface IPBlockMapper {

    void insertIPBlock(String ip);

}
