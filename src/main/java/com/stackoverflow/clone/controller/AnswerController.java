package com.stackoverflow.clone.controller;

import com.stackoverflow.clone.entity.Answer;
import com.stackoverflow.clone.entity.Question;
import com.stackoverflow.clone.service.AnswerService;
import com.stackoverflow.clone.service.QuestionService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Controller
@RequestMapping("/answer")
public class AnswerController {

    private final QuestionService questionService;
    private final AnswerService answerService;
    public AnswerController(QuestionService questionService, AnswerService answerService){
        this.questionService = questionService;
        this.answerService = answerService;
    }
    @PostMapping("/save")
    public String saveAnswer(@RequestParam("questionId") Long questionId,
                             @ModelAttribute("answer")Answer answer){

        Question question = questionService.findById(questionId);
        if (answer.getId() != null){
            question.setUpdatedAt(Timestamp.valueOf(LocalDateTime.now()));
        }
        answer.setQuestion(question);
        answerService.save(answer);
        return "redirect:/question/"+questionId;
    }
}
