package com.algorithm.algoprojectserver.serviceimpl;


import com.algorithm.algoprojectserver.dto.SiteDTO;
import com.algorithm.algoprojectserver.mapper.SiteLinkMapper;
import com.algorithm.algoprojectserver.service.SiteLinkService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SiteLinkServiceImpl implements SiteLinkService {

    SiteLinkMapper siteLinkMapper;

    public SiteLinkServiceImpl(SiteLinkMapper siteLinkMapper) {
        this.siteLinkMapper = siteLinkMapper;
    }

    @Override
    public void writeSiteLink(SiteDTO siteDTO) {
        siteLinkMapper.insertSiteLink(siteDTO);
    }

    @Override
    public List<SiteDTO> getSiteLinks() {
        return siteLinkMapper.selectSiteLink();
    }
}
