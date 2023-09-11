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

import java.util.*;

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
        User user = userService.findByUsername(authentication.getName());
        List<Question> userQuestions = questionService.findByUser(user);

        model.addAttribute("userQuestions", userQuestions);
        return "/user/profile";
    }

    @GetMapping("/users")
    public String users(Model model){

        List<User> users = userService.findAll();
//        List<Object[]> topTagsForEachUser = userService.findTop3TagsForEachUser();
        Map<Long, List<String>> userTopTagsMap = new HashMap<>();


//        for (Object[] result : topTagsForEachUser) {
//            Long userId = (Long) result[0];
//            Object tagValue = result[2];
//
//            String tagName = (tagValue != null) ? tagValue.toString() : null;
//
//            if (!userTopTagsMap.containsKey(userId)) {
//                userTopTagsMap.put(userId, new ArrayList<>());
//            }
//
//            userTopTagsMap.get(userId).add(tagName);
//            System.out.println(userTopTagsMap);
//        }

        for (User user : users) {
            user.setTopTags(userService.findTop3TagsByUserId(user.getId()));
//                List<String> topTags = userTopTagsMap.get(user.getId()).subList(0, Math.min(3, userTopTagsMap.get(user.getId()).size()));
//                user.setTopTags(topTags);
        }

        model.addAttribute("users", users);
        return "user/user-list";
    }

}
