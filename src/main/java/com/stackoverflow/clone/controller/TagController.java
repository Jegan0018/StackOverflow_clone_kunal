package com.stackoverflow.clone.controller;

import com.stackoverflow.clone.entity.Question;
import com.stackoverflow.clone.entity.Tag;
import com.stackoverflow.clone.service.QuestionService;
import com.stackoverflow.clone.service.TagService;
import com.stackoverflow.clone.util.TimeElapsedFormatter;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.*;
import java.util.stream.Collectors;

@Controller
public class TagController {

    private QuestionService questionService;
    private TagService tagService;

    public TagController(TagService tagService,QuestionService questionService) {
        this.tagService=tagService;
        this.questionService=questionService;
    }

    @GetMapping("/question/tags")
    public String listTags(Model model,
                           @RequestParam(value = "search", required = false) String search,
                           @RequestParam(value = "tab",defaultValue = "popular") String tab) {
        List<Tag> tags;
        Map<Tag,Integer> questionsCountByTag = new LinkedHashMap<>();

        if(search != null){
            tags = tagService.search(search, tab);
        }
        else if(tab==null){

            tags = tagService.findAll();
        }
        else if(tab.equals("name")){
            tags=tagService.findAllByTagNameAsc();
        }

        else{
            tags=tagService.findAllByCreatedAtDesc();
        }

        for (Tag tag : tags) {
            int count = questionService.countQuestionsByTag(tag);
            questionsCountByTag.put(tag,count);
        }


        if(tab != null && tab.equals("popular")){

            List<Map.Entry<Tag, Integer>> entryList = new ArrayList<>(questionsCountByTag.entrySet());
            entryList.sort((e1, e2) -> e2.getValue().compareTo(e1.getValue()));
            questionsCountByTag = entryList.stream()
                    .collect(Collectors.toMap(
                            Map.Entry::getKey,
                            Map.Entry::getValue,
                            (e1, e2) -> e1,
                            LinkedHashMap::new
                    ));
        }



        Map<Tag, String> formattedCreatedAt = new HashMap<>();
        for (Tag tag : questionsCountByTag.keySet()) {
            Date createdAt = tag.getCreatedAt();
            String formattedTimeElapsed = TimeElapsedFormatter.formatTimeElapsed(createdAt);
            formattedCreatedAt.put(tag, formattedTimeElapsed);
        }

        model.addAttribute("search", search);
        model.addAttribute("formattedCreatedAt", formattedCreatedAt);
        model.addAttribute("questionsCountByTag",questionsCountByTag);

        return "tag/list-tag";
    }

    @GetMapping("questions/tagged/{tagName}")
    public String listQuestionsByTags(Model model,
                           @PathVariable("tagName") String tagName) {
        List<Question> questions = tagService.findQuestionsByTagName(tagName);
        model.addAttribute("questions",questions);
        model.addAttribute("tagName",tagName);
        return "Home";
    }
}