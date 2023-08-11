package com.algorithm.algoprojectserver.serviceimpl;


import com.algorithm.algoprojectserver.compiler.ClangCompile;
import com.algorithm.algoprojectserver.compiler.JavaCompile;
import com.algorithm.algoprojectserver.compiler.PythonCompile;
import com.algorithm.algoprojectserver.config.CompileConstains;
import com.algorithm.algoprojectserver.mapper.ProblemMapper;
import com.algorithm.algoprojectserver.service.CompileService;
import com.algorithm.algoprojectserver.service.HistoryService;
import com.algorithm.algoprojectserver.service.PointService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class CompileServiceImpl implements CompileService {

    ProblemMapper problemMapper;
    HistoryService historyService;
    PointService pointService;
    JavaCompile javaCompile;
    ClangCompile clangCompile;
    PythonCompile pythonCompile;

    public CompileServiceImpl(ProblemMapper problemMapper,
                              HistoryService historyService,
                              PointService pointService,
                              JavaCompile javaCompile,
                              ClangCompile clangCompile,
                              PythonCompile pythonCompile) {
        this.problemMapper = problemMapper;
        this.historyService = historyService;
        this.pointService = pointService;
        this.javaCompile = javaCompile;
        this.clangCompile = clangCompile;
        this.pythonCompile = pythonCompile;
    }

    @Override
    @Transactional
    public String compileHandler(String code, String lang, Integer pageNum, HttpServletRequest request) {
        List<String> compileResults = new ArrayList<>();

        if (lang.equals("JAVA")) {
            compileResults = javaCompile.compileJavaCode(code, pageNum, request);
        }

        if (lang.equals("C")) {
            compileResults = clangCompile.compileClangCode(code, pageNum, request);
        }

        if (lang.equals("PYTHON")) {
            compileResults = pythonCompile.compilePythonCode(code, pageNum, request);
        }


        String compileResult = compileResultHandler(compileResults);
        historyService.madeBySolveHistory(compileResult, lang, pageNum);
        pointService.pointServiceHandler(compileResult, pageNum);


        return null;
    }

    private String compileResultHandler(List<String> compileResults) {


        for(String compileResult : compileResults) {
            if(compileResult.equals(CompileConstains.COMPILE_ERROR)) {
                return CompileConstains.COMPILE_ERROR;
            }
        }

        for(String compileResult : compileResults) {
            if(compileResult.equals(CompileConstains.COMPILE_FAIL))
                return CompileConstains.COMPILE_FAIL;
        }

        return CompileConstains.COMPILE_SUCCESS;

    }
}
