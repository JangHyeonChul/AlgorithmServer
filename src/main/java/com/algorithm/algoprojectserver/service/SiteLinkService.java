package com.algorithm.algoprojectserver.service;


import com.algorithm.algoprojectserver.dto.SiteDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface SiteLinkService {

    void writeSiteLink(SiteDTO siteDTO);

    List<SiteDTO> getSiteLinks();


}
