package com.stackoverflow.clone.repository;

import com.stackoverflow.clone.entity.Answer;
import com.stackoverflow.clone.entity.Question;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AnswerRepository extends JpaRepository<Answer, Long> {

    List<Answer> findByQuestionId(Long id);

//    Page<Question> findAllByNotAnswered(Pageable pageable);
}
