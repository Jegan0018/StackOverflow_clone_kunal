package com.stackoverflow.clone.controller;

import com.stackoverflow.clone.entity.*;
import com.stackoverflow.clone.service.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Controller
@RequestMapping("/")
public class QuestionController {

    private final QuestionService questionService;
    private final AnswerService answerService;
    private final TagService tagService;
    private final UserService userService;
    private final CommentService commentService;

    public QuestionController(QuestionService questionService, UserService userService,
                              TagService tagService, AnswerService answerService, CommentService commentService) {
        this.questionService = questionService;
        this.userService = userService;
        this.tagService = tagService;
        this.answerService = answerService;
        this.commentService = commentService;
    }

    @GetMapping("/")
    public String home(Model model) {
        
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user= userService.findByUsername(authentication.getName());
        List<Question> recent10Question = questionService.findTop10ByOrderByCreatedAtDesc();
        if (user != null) {
            model.addAttribute("user", user);
        }
        model.addAttribute("recent10Question", recent10Question);
        return "Home-Page";
    }
    @GetMapping("/questions")
    public String questions(Model model){
        List<Question> questions = questionService.findAll();
        model.addAttribute("questions",questions);
        return "all-question";
    }

    @GetMapping("/new-question")
    public String newQuestion(Model model){
        Question question = new Question();
        model.addAttribute("question",question);
        return "question/new-question";
    }

    @PostMapping("/save")
    public String saveQuestion(@ModelAttribute("question") Question question,
                               @RequestParam(value = "tagNames", required = false) String tagNames) {
        if (question.getId()!=null){
            question.setUpdatedAt(Timestamp.valueOf(LocalDateTime.now()));
        }
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user= userService.findByUsername(authentication.getName());
        Set<Tag> tags = tagService.findOrCreateTag(tagNames);

        question.setUser(user);
        question.setTags(tags);
        questionService.save(question);
        return "redirect:/question/"+question.getId();
    }

    @GetMapping("/question/{questionId}")
    public String viewQuestion(@PathVariable("questionId") Long questionId,
                               Model model){
        Question question = questionService.findById(questionId);
        List<Answer> answers = answerService.findByQuestionId(questionId);
        Answer answer = new Answer();

        for (Answer ans : answers) {
            List<Comment> comments = commentService.findByAnswerId(ans.getId());
            ans.setComments(comments);
        }

        model.addAttribute("user", question.getUser());
        model.addAttribute("answers", answers);
        model.addAttribute("answer", answer);
        model.addAttribute("question",question);
        return "question/view-question";
    }

    @PostMapping("/question/editQuestion/{questionId}")
    public String editPost(@PathVariable("questionId") Long questionId,
                           Model model) {
        Question question = questionService.findById(questionId);
        Set<Tag> tags = question.getTags();
        StringBuilder tagNamesBuilder = new StringBuilder();
        for (Tag tag : tags) {
            tagNamesBuilder.append(tag.getName()).append(" ");
        }
        String tagNames = tagNamesBuilder.toString().trim();
        model.addAttribute("question", question);
        model.addAttribute("tagNames", tagNames);
        return "question/edit-question";
    }

    @PostMapping("question/delete/{deleteId}")
    public String delete(@PathVariable("deleteId") Long deleteId) {
        questionService.deleteById(deleteId);
        return "redirect:/";
    }
}