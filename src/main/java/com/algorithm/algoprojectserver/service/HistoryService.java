package com.algorithm.algoprojectserver.service;

/**
 * File Name : HistoryService
 * Description : 제출내역 댓글 작성 Service Mapper
 * Update : 2023-08-21
 */

import com.algorithm.algoprojectserver.dto.SolveHistoryDTO;

import java.util.List;

public interface HistoryService {

    void madeBySolveHistory(String compileResult, String lang, Integer pageNum);
    List<SolveHistoryDTO> getSolveHistorys(String userid, Integer p_no);

}
