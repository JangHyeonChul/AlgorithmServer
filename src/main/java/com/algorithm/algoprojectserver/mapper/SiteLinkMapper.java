package com.algorithm.algoprojectserver.mapper;

/**
 * File Name : SiteLinkMapper
 * Description : 관련사이트 관련 Mybatis 데이터베이스 Mapping
 * Update : 2023-08-18
 */


import com.algorithm.algoprojectserver.dto.SiteDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SiteLinkMapper {

    void insertSiteLink(SiteDTO siteDTO);

    List<SiteDTO> selectSiteLink();

}
