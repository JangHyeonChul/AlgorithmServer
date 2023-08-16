package com.algorithm.algoprojectserver.controller.admin;

/**
 * File Name : AdminLinkController
 * Description : 어드민 관련사이트 컨트롤러
 * Update : 2023-08-15
 */

import com.algorithm.algoprojectserver.dto.SiteDTO;
import com.algorithm.algoprojectserver.service.SiteLinkService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
@Slf4j
public class AdminLinkController {

    SiteLinkService siteLinkService;

    public AdminLinkController(SiteLinkService siteLinkService) {
        this.siteLinkService = siteLinkService;
    }

    /*
    * API : /admin/link
    * Method : GET
    * DESCRIPTION : 어드민 권한을 가진 계정의 관련 사이트 등록 창을 이동
    * */

    @GetMapping("/link")
    public String adminLink(@ModelAttribute("siteLinkDTO") SiteDTO siteDTO) {
        return "/admin/admin-site";
    }

    /*
     * API : /admin/link
     * Method : POST
     * DESCRIPTION : 어드민 권한을 가진 계정의 관련 사이트 등록 수행
     * */

    @PostMapping("/link")
    public String adminLinkSubmit(SiteDTO siteDTO, HttpServletRequest request) {

        log.info("[요청 IP : {}] 관련 사이트 등록 수행", request.getRemoteAddr());

        siteLinkService.writeSiteLink(siteDTO);

        return "redirect:/developer";
    }
}
