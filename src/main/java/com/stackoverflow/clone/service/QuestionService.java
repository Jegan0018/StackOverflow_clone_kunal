package com.stackoverflow.clone.service;

import com.stackoverflow.clone.entity.Question;
import com.stackoverflow.clone.entity.Tag;
import com.stackoverflow.clone.entity.User;

import java.util.List;

public interface QuestionService {
    void save(Question question);

    Question findById(Long questionId);

    List<Question> findAll();

    Question deleteById(Long deleteId);

    int countQuestionsByTag(Tag tag);
    List<Question> findByUser(User user);
    List<Question> findTop10ByOrderByCreatedAtDesc();
}
