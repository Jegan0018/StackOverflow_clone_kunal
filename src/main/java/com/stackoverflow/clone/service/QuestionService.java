package com.stackoverflow.clone.service;

import com.stackoverflow.clone.entity.Question;
import com.stackoverflow.clone.entity.Tag;
import com.stackoverflow.clone.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface QuestionService {
    void save(Question question);

    Question findById(Long questionId);

    List<Question> findAll();

    Question deleteById(Long deleteId);

    int countQuestionsByTag(Tag tag);
    List<Question> findByUser(User user);
    List<Question> findTop10ByOrderByCreatedAtDesc();

    List<Question> findQuestionsBySearch(String search);

    Page<Question> findAllByCreatedAtDesc(Pageable pageable);

    List<Question> findAllByUserName(String username);
}
