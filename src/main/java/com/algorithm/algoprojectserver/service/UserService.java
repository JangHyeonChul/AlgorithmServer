package com.algorithm.algoprojectserver.service;

/**
 * File Name : UserService
 * Description : 유저 데이터 관련 작성 Service Mapper
 * Update : 2023-08-21
 */

import com.algorithm.algoprojectserver.dto.MemberDTO;
import com.algorithm.algoprojectserver.dto.MyInfoDTO;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public interface UserService {

    MemberDTO findByUserIdOrEmail(String userData);
    void registerUser(MemberDTO userDTO);
    void updateUserPassword(String username, String password);
    void updateUserAuthGrade(String username, String grade);

    MyInfoDTO getUserInfo(String username);

    String userModifyInfoValidCheck(String username, String user, String message);
    void ipblock(String ip);

    void updateUserImg(String userId, String img);
    String profileImgUpload(MultipartFile imgfile, String userId) throws IOException;

}
