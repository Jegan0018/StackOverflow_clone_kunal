package com.stackoverflow.clone.service.implementation;

import com.stackoverflow.clone.entity.Question;
import com.stackoverflow.clone.repository.QuestionRepository;
import com.stackoverflow.clone.service.QuestionService;

public class QuestionServiceImpl implements QuestionService {

    private QuestionRepository questionRepository;

    public QuestionServiceImpl(QuestionRepository questionRepository) {
        this.questionRepository=questionRepository;
    }

    @Override
    public void save(Question question) {
        questionRepository.save(question);
    }
}
