package com.algorithm.algoprojectserver.service;

/**
 * File Name : CompileService
 * Description : 컴파일 관련 Service Mapper
 * Update : 2023-08-21
 */

import jakarta.servlet.http.HttpServletRequest;

public interface CompileService {

    String compileHandler(String code, String lang, Integer pageNum, HttpServletRequest request);



}
