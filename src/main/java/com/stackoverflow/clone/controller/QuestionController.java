package com.stackoverflow.clone.controller;

import com.stackoverflow.clone.entity.Question;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class QuestionController {

    @PostMapping("/new-question")
    public String newQuestion(Model model){
        Question question = new Question();
        model.addAttribute("question",question);
        return "new-question";
    }
}
