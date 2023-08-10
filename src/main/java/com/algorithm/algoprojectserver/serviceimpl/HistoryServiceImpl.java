package com.algorithm.algoprojectserver.serviceimpl;


import com.algorithm.algoprojectserver.Time;
import com.algorithm.algoprojectserver.config.CompileConstains;
import com.algorithm.algoprojectserver.dto.SolveHistoryDTO;
import com.algorithm.algoprojectserver.mapper.ProblemMapper;
import com.algorithm.algoprojectserver.service.HistoryService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class HistoryServiceImpl implements HistoryService {

    ProblemMapper problemMapper;

    public HistoryServiceImpl(ProblemMapper problemMapper) {
        this.problemMapper = problemMapper;
    }

    @Override
    public void madeBySolveHistory(String compileResult, String lang, Integer pageNum) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication != null) {
            String name = SecurityContextHolder.getContext().getAuthentication().getName();
            String compileMessage = HistoryMessage(compileResult);
            String compileCheck = HistorySuccessMessage(compileResult);

            SolveHistoryDTO solveHistoryDTO = new SolveHistoryDTO(name, pageNum, compileCheck, lang, compileMessage);



            problemMapper.insertSolveHistory(solveHistoryDTO);
        }

    }

    @Override
    public List<SolveHistoryDTO> getSolveHistorys(String userid, Integer p_no) {
        List<SolveHistoryDTO> solveHistoryDTOS = problemMapper.selectSolveHistorys(userid, p_no);

        return solveHistoryTimeConverter(solveHistoryDTOS);
    }


    private String HistoryMessage(String compileResult) {

        boolean compileFail = compileResult.equals(CompileConstains.COMPILE_FAIL);
        boolean compileError = compileResult.equals(CompileConstains.COMPILE_ERROR);

        if (compileFail) {
            return CompileConstains.COMPILE_FAIL_MESSAGE;
        }

        if (compileError) {
            return CompileConstains.COMPILE_ERROR_MESSAGE;
        }

        return "";

    }

    private String HistorySuccessMessage(String compileResult) {

        boolean compileFail = compileResult.equals(CompileConstains.COMPILE_FAIL);
        boolean compileError = compileResult.equals(CompileConstains.COMPILE_ERROR);

        if (compileFail|| compileError) {
            return CompileConstains.COMPILE_FAIL_CHECK_MESSAGE;
        } else {
            return CompileConstains.COMPILE_SUCCESS_MESSAGE;
        }

    }

    private List<SolveHistoryDTO> solveHistoryTimeConverter(List<SolveHistoryDTO> solveHistoryDTOs) {

        for (SolveHistoryDTO solveHistoryDTO : solveHistoryDTOs) {
            LocalDateTime submitDate = solveHistoryDTO.getSubmit_date();
            String converterTime = Time.txtDate(submitDate);
            solveHistoryDTO.setTransTime(converterTime);
        }

        return solveHistoryDTOs;

    }

}
