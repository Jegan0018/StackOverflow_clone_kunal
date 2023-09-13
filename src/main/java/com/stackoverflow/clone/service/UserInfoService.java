package com.stackoverflow.clone.service;

import com.stackoverflow.clone.entity.User;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class UserInfoService {

    private final UserService userService;

    public UserInfoService(UserService userService) {
        this.userService = userService;
    }

    public User currentUser(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null) {
            User user = userService.findByUsername(authentication.getName());
            return user;
        }
        return new User();
    }
}
