package com.algorithm.algoprojectserver.mapper;

/**
 * File Name : TokenLinkMapper
 * Description : 이메일 인증 토큰 관련 Mybatis 데이터베이스 Mapping
 * Update : 2023-08-18
 */


import com.algorithm.algoprojectserver.dto.TokenLinkDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.time.LocalDateTime;

@Mapper
public interface TokenLinkMapper {

    void insertTokenLink(TokenLinkDTO tokenLinkDTO);
    void updateTokenLink(TokenLinkDTO tokenLinkDTO);
    void updateTokenLinkTrial(String username);
    void updateTokenLinkReset(String username);
    void updateTokenLinkResetTime(@Param("username") String username, @Param("resetTime") LocalDateTime resetTime);
    void updateTokenLinkUUID(@Param("username") String username, @Param("uuid") String uuid);

    TokenLinkDTO selectTokenLink(String username);

}
