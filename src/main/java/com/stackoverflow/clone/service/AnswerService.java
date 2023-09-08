package com.stackoverflow.clone.service;

import com.stackoverflow.clone.entity.Answer;

import java.util.List;

public interface AnswerService {

    void save(Answer answer);

    List<Answer> findByQuestionId(Long questionId);

    Answer findById(Long id);

    void deleteById(Long id);

}
