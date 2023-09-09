package com.stackoverflow.clone.service.implementation;

import com.stackoverflow.clone.entity.Comment;
import com.stackoverflow.clone.repository.CommentRepository;
import com.stackoverflow.clone.service.CommentService;
import org.springframework.stereotype.Service;

@Service
public class CommentServiceImpl implements CommentService {
    private final CommentRepository commentRepository;

    public CommentServiceImpl(CommentRepository commentRepository){
        this.commentRepository = commentRepository;
    }

    @Override
    public void save(Comment comment) {
        commentRepository.save(comment);
    }
}
