package com.algorithm.algoprojectserver.serviceimpl;


import com.algorithm.algoprojectserver.config.CompileConstains;
import com.algorithm.algoprojectserver.dto.SolveDTO;
import com.algorithm.algoprojectserver.mapper.ProblemMapper;
import com.algorithm.algoprojectserver.service.PointService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class PointServiceImpl implements PointService {

    ProblemMapper problemMapper;

    public PointServiceImpl(ProblemMapper problemMapper) {
        this.problemMapper = problemMapper;
    }

    @Override
    public void pointServiceHandler(String result, int pageNum) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        boolean resultEqualsSuccess = result.equals(CompileConstains.COMPILE_SUCCESS);
        SolveDTO solveMember = problemMapper.selectSolveMember(username, pageNum);

        System.out.println("solveMember = " + solveMember);

        if (resultEqualsSuccess && solveMember == null) {
            updateAddPoint(10, username);
            problemMapper.insertSolveMember(pageNum, username);
        }

    }


    @Override
    public void updateAddPoint(int point, String username) {
        problemMapper.updateAddPoint(point, username);
    }


}
