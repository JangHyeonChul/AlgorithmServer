package com.algorithm.algoprojectserver.controller.admin;

/**
 * File Name : AdminProblemController
 * Description : 어드민 문제 알고리즘 등록 관련 컨트롤러
 * Update : 2023-08-15
 */

import com.algorithm.algoprojectserver.dto.AnswerDTO;
import com.algorithm.algoprojectserver.dto.ProblemDTO;
import com.algorithm.algoprojectserver.service.ProblemService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@Controller
@Slf4j
@RequestMapping("/admin")
public class AdminProblemController {

    ProblemService problemService;

    public AdminProblemController(ProblemService problemService) {
        this.problemService = problemService;
    }


    /*
     * API : /admin/problem
     * Method : GET
     * DESCRIPTION : 문제등록 창으로 이동
     * */

    @GetMapping("/problem")
    public String admin(Model model) {

        model.addAttribute("problemDTO", new ProblemDTO());
        model.addAttribute("answerDTO", new AnswerDTO());

        return "admin/admin-problem";
    }

    /*
     * API : /admin/problem
     * Method : POST
     * DESCRIPTION : 문제등록을 위한 기능 수행
     * */

    @PostMapping("/problem")
    @Transactional
    public String adminproblem(@ModelAttribute ProblemDTO problemDTO,
                               @RequestParam("inputs[]") ArrayList<String> inputs,
                               @RequestParam("outputs[]") ArrayList<String> outputs,
                               HttpServletRequest request) {

        String name = SecurityContextHolder.getContext().getAuthentication().getName();
        problemDTO.setUser_id(name);

        log.info("[요청 IP : {}] {} 아이디의 문제등록 요청", request.getRemoteAddr(), name);

        problemService.writeProblem(problemDTO);
        int p_no = problemService.getLastInsertID();
        problemService.writeProblemAnswer(inputs, outputs, p_no);



        return "redirect:/admin/problem";
    }
}
