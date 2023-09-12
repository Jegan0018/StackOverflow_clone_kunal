package com.stackoverflow.clone.service.implementation;

import com.stackoverflow.clone.entity.Answer;
import com.stackoverflow.clone.entity.User;
import com.stackoverflow.clone.repository.AnswerRepository;
import com.stackoverflow.clone.service.AnswerService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

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

    @Override
    public List<Answer> findByQuestionId(Long questionId) {
        return answerRepository.findByQuestionId(questionId);
    }

    @Override
    public Answer findById(Long id) {
        return answerRepository.findById(id).get();
    }

    @Override
    public void deleteById(Long id) {
        answerRepository.deleteById(id);
    }

    @Override
    public List<Answer> findFirst5ByUserOrderByCreatedAtDesc(User user) {
        return answerRepository.findFirst5ByUserOrderByCreatedAtDesc(user);
    }

    @Override
    public Page<Answer> findAllByUserOrderByCreatedAtDesc(User user, Pageable pageable) {
        return answerRepository.findAllByUserOrderByCreatedAtDesc(user,pageable);
    }
}
