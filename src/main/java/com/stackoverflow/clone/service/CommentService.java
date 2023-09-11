package com.stackoverflow.clone.service;

import com.stackoverflow.clone.entity.Comment;

import java.util.List;

public interface CommentService {
    void save(Comment comment);
    List<Comment> findByAnswerId(Long id);
    void deleteById(Long id);
}
