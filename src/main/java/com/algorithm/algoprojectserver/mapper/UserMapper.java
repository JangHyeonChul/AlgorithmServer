package com.algorithm.algoprojectserver.mapper;

/**
 * File Name : UserMapper
 * Description : 유저 관련 Mybatis 데이터베이스 Mapping
 * Update : 2023-08-18
 */


import com.algorithm.algoprojectserver.dto.MemberDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface UserMapper {

    MemberDTO findByUserData(String userData);
    void insertUser(MemberDTO memberDTO);
    void updateUserMessage(@Param("username")String username, @Param("message") String message);
    void updateUserPassword(@Param("username") String username, @Param("password") String password);
    void updateUserAuthGrade(@Param("username") String username, @Param("grade") String grade);



    MemberDTO findByUserId(String userId);
    void updateUserProfileImg(@Param("userId") String userId, @Param("img") String img);
}
