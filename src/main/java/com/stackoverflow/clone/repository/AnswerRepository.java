package com.stackoverflow.clone.repository;

import com.stackoverflow.clone.entity.Answer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnswerRepository extends JpaRepository<Answer, Long> {
}
