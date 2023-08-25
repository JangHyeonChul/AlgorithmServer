package com.algorithm.algoprojectserver.service;

/**
 * File Name : SiteLinkService
 * Description : 관련 사이트 작성 Service Mapper
 * Update : 2023-08-21
 */

import com.algorithm.algoprojectserver.dto.SiteDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface SiteLinkService {

    void writeSiteLink(SiteDTO siteDTO);

    List<SiteDTO> getSiteLinks();


}
