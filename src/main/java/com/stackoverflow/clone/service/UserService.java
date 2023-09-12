package com.stackoverflow.clone.service;

import com.stackoverflow.clone.entity.Question;
import com.stackoverflow.clone.entity.Tag;
import com.stackoverflow.clone.entity.User;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;

public interface UserService {
    UserDetails loadUserByUsername(String username);
    User findByUsername(String username);

    List<User> findAll();
    List<Object[]> findTop3TagsForEachUser();

    List<Tag> findTop3TagsByUserId(@Param("userId") Long userId);

    User findById(int userId);
}
