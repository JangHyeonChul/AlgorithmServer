package com.algorithm.algoprojectserver.controller;

/**
 * File Name : UploadController
 * Description : 이메일 인증 토큰 관련 컨트롤러
 * Update : 2023-08-18
 */

import com.algorithm.algoprojectserver.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;


@RestController
@Slf4j
public class UploadController {

    UserService userService;


    public UploadController(UserService userService) {
        this.userService = userService;
    }

    /*
     * API : /upload
     * Method : POST
     * DESCRIPTION : 유저 프로필 이미지 업로드 기능 수행
     * */

    @PostMapping("/upload")
    public String userImgUpload(@RequestParam("imgfile") MultipartFile imgfile,
                                @RequestParam("userID") String userID,
                                HttpServletRequest request) throws IOException {

        log.info("[요청 IP : {}] 요청 유저 아이디 {} : 프로필 갱신 수행", request.getRemoteAddr(), userID);

        String imgURI = userService.profileImgUpload(imgfile, userID);

        userService.updateUserImg(userID, imgURI);

        log.info("[요청 IP : {}] 요청 유저 아이디 {} : 프로필 갱신 완료", request.getRemoteAddr(), userID);

        return imgURI;


    }

}
