package com.algorithm.algoprojectserver.mapper;


import com.algorithm.algoprojectserver.dto.SiteDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SiteLinkMapper {

    void insertSiteLink(SiteDTO siteDTO);

    List<SiteDTO> selectSiteLink();

}
