package com.stackoverflow.clone.service.implementation;

import com.stackoverflow.clone.entity.Question;
import com.stackoverflow.clone.entity.Tag;
import com.stackoverflow.clone.entity.User;
import com.stackoverflow.clone.repository.QuestionRepository;
import com.stackoverflow.clone.service.QuestionService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestionServiceImpl implements QuestionService {

    private QuestionRepository questionRepository;

    public QuestionServiceImpl(QuestionRepository questionRepository) {
        this.questionRepository=questionRepository;
    }

    @Override
    public void save(Question question) {
        questionRepository.save(question);
    }

    @Override
    public Question findById(Long questionId) {
        return questionRepository.findById(questionId).get();
    }

    @Override
    public List<Question> findAll() {
        return questionRepository.findAll();
    }

    @Override
    public Question deleteById(Long deleteId) {
        Question question = questionRepository.findById(deleteId).orElse(null);
        if (question != null) {
            questionRepository.delete(question);
        }
        return question;
    }

    @Override
    public int countQuestionsByTag(Tag tag) {
        return questionRepository.countQuestionsByTags(tag);
    }

    @Override
    public List<Question> findByUser(User user) {
        return questionRepository.findByUser(user);
    }

    @Override
    public List<Question> findTop10ByOrderByCreatedAtDesc() {
        return questionRepository.findTop10ByOrderByCreatedAtDesc();
    }

    @Override
    public List<Question> findQuestionsBySearch(String search) {
        List<Question> questions=questionRepository.findQuestionsBySearch(search);
        return questions;
    }

    @Override
    public Page<Question> findAllByCreatedAtDesc(Pageable pageable) {
        return questionRepository.findAllByOrderByCreatedAtDesc(pageable);
    }

    @Override
    public List<Question> findAllByUserName(String username) {
        return questionRepository.findAllByUserName(username);
    }
}
