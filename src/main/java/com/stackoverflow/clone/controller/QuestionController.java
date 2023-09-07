package com.stackoverflow.clone.controller;

import com.stackoverflow.clone.entity.Question;
import com.stackoverflow.clone.entity.Tag;
import com.stackoverflow.clone.service.QuestionService;
import com.stackoverflow.clone.service.TagService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@Controller
@RequestMapping("/")
public class QuestionController {

    private QuestionService questionService;

    private TagService tagService;

    public QuestionController(QuestionService questionService,TagService tagService) {
        this.questionService=questionService;
        this.tagService=tagService;
    }

    @GetMapping("/new-question")
    public String newQuestion(Model model){
        Question question = new Question();
        model.addAttribute("question",question);
        return "new-question";
    }

    @PostMapping("/save")
    public String saveQuestion(@ModelAttribute("question") Question question, @RequestParam("tagInput") String tagInput) {
        Set<Tag> tags = tagService.findOrCreateTag(tagInput);
        question.setTags(tags);
        questionService.save(question);
        return "redirect:/full-question"+question.getId();
    }
}
