package com.stackoverflow.clone.controller;

import com.stackoverflow.clone.entity.Question;
import com.stackoverflow.clone.entity.Tag;
import com.stackoverflow.clone.service.TagService;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class TagController {
    private TagService tagService;

    public TagController(TagService tagService) {
        this.tagService=tagService;
    }

//    @GetMapping("/question/tags")
//    public String listTags(Model model){
//        List<Tag> tags = tagService.findAll();
//        model.addAttribute("tags",tags);
//        return "tag/list-tag";
//    }

    @GetMapping("/question/tags")
    public String listTags(Model model,
                           @RequestParam(name = "tab", required = false) String sort) {
        List<Tag> tags;
        if(sort==null) {
            tags = tagService.findAll();
        } else {
            tags=tagService.findAllByCreatedAtDesc();
        }
        model.addAttribute("tags", tags);
        return "tag/list-tag";
    }

    @GetMapping("questions/tagged/{tagName}")
    public String listQuestionsByTags(Model model,
                           @PathVariable("tagName") String tagName) {
        List<Question> questions = tagService.findQuestionsByTagName(tagName);
        System.out.println("Questions "+questions);
        model.addAttribute("questions",questions);
        model.addAttribute("tagName",tagName);
        return "Home";
    }
}
