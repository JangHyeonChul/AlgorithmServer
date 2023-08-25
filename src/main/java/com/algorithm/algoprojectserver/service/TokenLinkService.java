package com.algorithm.algoprojectserver.service;

/**
 * File Name : TokenLinkService
 * Description : 이메일 인증 토큰 관련 Service Mapper
 * Update : 2023-08-21
 */

import com.algorithm.algoprojectserver.dto.TokenLinkDTO;

import java.util.Map;

public interface TokenLinkService {


    void tokenLinkHandler(String username);
    void createToken(String username);

    Map<String, String> getTokenData(String username);
    TokenLinkDTO getTokenLink(String username);
}
