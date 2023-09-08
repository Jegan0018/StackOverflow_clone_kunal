package com.stackoverflow.clone.controller;

import com.stackoverflow.clone.entity.Question;
import com.stackoverflow.clone.service.QuestionService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/answer")
public class AnswerController {

    private final QuestionService questionService;

    public AnswerController(QuestionService questionService){
        this.questionService = questionService;
    }
    @PostMapping("/save")
    public String saveAnswer(@RequestParam("questionId") Long questionId,
                             @){

        Question question = questionService.findById(questionId);

    }
}
