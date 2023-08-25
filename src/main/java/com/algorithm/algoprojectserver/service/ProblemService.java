package com.algorithm.algoprojectserver.service;

/**
 * File Name : ProblemService
 * Description : 문제 관련 Service Mapper
 * Update : 2023-08-21
 */


import com.algorithm.algoprojectserver.PageHandler;
import com.algorithm.algoprojectserver.dto.AnswerDTO;
import com.algorithm.algoprojectserver.dto.ProblemDTO;
import com.algorithm.algoprojectserver.dto.ProblemOptionDTO;
import com.algorithm.algoprojectserver.dto.SolveDTO;

import java.util.List;
import java.util.Map;


public interface ProblemService {

    List<ProblemDTO> getAllProblems(int offset);
    List<ProblemDTO> getAllProblemsByType(String typename, int offset);
    List<ProblemDTO> getAllProblemsByOptions(ProblemOptionDTO problemOptionDTO, int offset);
    List<ProblemDTO> getAllProblemsBySearch(String keyword, int offset);
    ProblemDTO getProblem(int pageNum);
    List<AnswerDTO> getProblemAnswers(int pageNum);
    List<SolveDTO> getAllSolveMember(String username);



    void addPoint(int point, String username);

    SolveDTO getSolveMember(String username,int p_no);

    void writeSolveMember(int p_no, String username);
    void writeProblem(ProblemDTO problemDTO);
    void writeProblemAnswer(List<String> input, List<String> output, int p_no);

    int getCountAllProblem();
    int getCountAllProblemsByOption(ProblemOptionDTO problemOptionDTO);
    Integer getCountAllProblemsBySearch(String keyword);

    Map<String, Integer> getProblemTypeCountMap();
    int getProblemTypeCount(String typename);
    int getPageOffset(int page, PageHandler pageHandler);
    int getLastInsertID();
    int getRandomPage(int maxNum);

    void deleteProblem(int pageNum);





}
