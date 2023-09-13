package com.stackoverflow.clone.service.implementation;

import com.stackoverflow.clone.entity.Question;
import com.stackoverflow.clone.entity.Tag;
import com.stackoverflow.clone.entity.User;
import com.stackoverflow.clone.entity.Vote;
import com.stackoverflow.clone.repository.QuestionRepository;
import com.stackoverflow.clone.repository.VoteRepository;
import com.stackoverflow.clone.service.QuestionService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestionServiceImpl implements QuestionService {

    private QuestionRepository questionRepository;

    private VoteRepository voteRepository;

    public QuestionServiceImpl(QuestionRepository questionRepository,VoteRepository voteRepository) {
        this.questionRepository=questionRepository;
        this.voteRepository=voteRepository;
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
    public List<Question> findQuestionsByUserAndTag(Long userId, String tagName) {
        return questionRepository.findQuestionsByUserAndTag(userId,tagName);
    }

    @Override
    public List<Question> findFirst5ByUserOrderByCreatedAtDesc(User user) {
        return questionRepository.findFirst5ByUserOrderByCreatedAtDesc(user);
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
    
    @Override
    public Vote findVoteByUserAndQuestion(User user, Question question) {
        return voteRepository.findVoteByUserAndQuestion(user,question);
    }

    @Override
    public void updateVote(Vote existingVote) {
        voteRepository.save(existingVote);
    }

    @Override
    public void createVote(Vote vote) {
        voteRepository.save(vote);
    }

//    @Override
//    public void deleteVote(Long voteId) {
//        voteRepository.deleteById(voteId);
//    }
}
