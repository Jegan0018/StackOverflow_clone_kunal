package com.stackoverflow.clone.repository;

import com.stackoverflow.clone.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {
}
