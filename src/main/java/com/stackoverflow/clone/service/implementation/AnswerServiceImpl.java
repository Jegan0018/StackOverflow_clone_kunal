package com.stackoverflow.clone.service.implementation;

import com.stackoverflow.clone.entity.Answer;
import com.stackoverflow.clone.repository.AnswerRepository;
import com.stackoverflow.clone.service.AnswerService;
import org.springframework.stereotype.Service;

@Service
public class AnswerServiceImpl implements AnswerService {
    private final AnswerRepository answerRepository;

    public AnswerServiceImpl(AnswerRepository answerRepository){
        this.answerRepository = answerRepository;
    }

    @Override
    public void save(Answer answer) {
        answerRepository.save(answer);
    }
}
