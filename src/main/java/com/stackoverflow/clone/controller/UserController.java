package com.stackoverflow.clone.controller;

import com.stackoverflow.clone.entity.Question;
import com.stackoverflow.clone.entity.User;
import com.stackoverflow.clone.service.QuestionService;
import com.stackoverflow.clone.service.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class UserController {
    private final QuestionService questionService;
    private final UserService userService;

    public UserController(QuestionService questionService, UserService userService){
        this.questionService = questionService;
        this.userService = userService;
    }

    @GetMapping("/profile")
    public String profile(Model model){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user= userService.findByUsername(authentication.getName());
        List<Question> userQuestions = questionService.findByUser(user);

        model.addAttribute("userQuestions", userQuestions);
        return "/user/profile";
    }
}
