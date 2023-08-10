package com.algorithm.algoprojectserver.controller.admin;


import com.algorithm.algoprojectserver.dto.AnswerDTO;
import com.algorithm.algoprojectserver.dto.ProblemDTO;
import com.algorithm.algoprojectserver.service.ProblemService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@Controller
@RequestMapping("/admin")
public class AdminProblemController {

    ProblemService problemService;

    public AdminProblemController(ProblemService problemService) {
        this.problemService = problemService;
    }

    @GetMapping("/problem")
    public String admin(Model model) {

        model.addAttribute("problemDTO", new ProblemDTO());
        model.addAttribute("answerDTO", new AnswerDTO());

        return "admin/admin-problem";
    }

    @PostMapping("/problem")
    @Transactional
    public String adminproblem(@ModelAttribute ProblemDTO problemDTO,
                               @RequestParam("inputs[]") ArrayList<String> inputs,
                               @RequestParam("outputs[]") ArrayList<String> outputs) {
        String name = SecurityContextHolder.getContext().getAuthentication().getName();
        problemDTO.setUser_id(name);

        problemService.writeProblem(problemDTO);
        int p_no = problemService.getLastInsertID();
        problemService.writeProblemAnswer(inputs, outputs, p_no);



        return "redirect:/admin/problem";
    }
}
