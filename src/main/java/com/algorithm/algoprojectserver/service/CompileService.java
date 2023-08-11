package com.algorithm.algoprojectserver.service;


import jakarta.servlet.http.HttpServletRequest;

public interface CompileService {

    String compileHandler(String code, String lang, Integer pageNum, HttpServletRequest request);


}
