package com.stackoverflow.clone.service;

import com.stackoverflow.clone.entity.Question;
import com.stackoverflow.clone.entity.User;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;

public interface UserService {
    UserDetails loadUserByUsername(String username);
    User findByUsername(String username);

}
