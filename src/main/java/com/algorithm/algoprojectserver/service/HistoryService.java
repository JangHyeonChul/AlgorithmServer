package com.algorithm.algoprojectserver.service;


import com.algorithm.algoprojectserver.dto.SolveHistoryDTO;

import java.util.List;

public interface HistoryService {

    void madeBySolveHistory(String compileResult, String lang, Integer pageNum);
    List<SolveHistoryDTO> getSolveHistorys(String userid, Integer p_no);

}
