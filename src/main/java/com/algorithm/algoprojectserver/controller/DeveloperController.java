package com.algorithm.algoprojectserver.controller;

/**
 * File Name : DeveloperController
 * Description : 만든사람 관련 컨트롤러
 * Update : 2023-08-18
 */

import com.algorithm.algoprojectserver.dto.SiteDTO;
import com.algorithm.algoprojectserver.service.SiteLinkService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;


@Controller
public class DeveloperController {

    SiteLinkService siteLinkService;

    public DeveloperController(SiteLinkService siteLinkService) {
        this.siteLinkService = siteLinkService;
    }

    /*
     * API : /developer
     * Method : GET
     * DESCRIPTION : 프로젝트 만든사람에 대한 정보 화면을 불러오는 기능 수행
     * */

    @GetMapping("/developer")
    public String developer(Model model) {

        List<SiteDTO> siteLinks = siteLinkService.getSiteLinks();

        model.addAttribute("siteDTO", siteLinks);


        return "developer";
    }
}
