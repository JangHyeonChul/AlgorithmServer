package com.algorithm.algoprojectserver.service;


import com.algorithm.algoprojectserver.dto.TokenLinkDTO;

import java.util.Map;

public interface TokenLinkService {


    void tokenLinkHandler(String username);
    void createToken(String username);

    Map<String, String> getTokenData(String username);
    TokenLinkDTO getTokenLink(String username);
}
