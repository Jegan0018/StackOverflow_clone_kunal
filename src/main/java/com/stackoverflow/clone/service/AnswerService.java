package com.stackoverflow.clone.service;

import com.stackoverflow.clone.entity.Answer;
import com.stackoverflow.clone.entity.Question;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface AnswerService {

    void save(Answer answer);

    List<Answer> findByQuestionId(Long questionId);

    Answer findById(Long id);

    void deleteById(Long id);

//    Page<Question> findAllByNotAnswered(Pageable pageable);
}
