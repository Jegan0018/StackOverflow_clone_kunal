package com.stackoverflow.clone.repository;

import com.stackoverflow.clone.entity.Question;
import com.stackoverflow.clone.entity.Tag;
import com.stackoverflow.clone.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface QuestionRepository extends JpaRepository<Question,Long> {
    int countQuestionsByTags(Tag tag);
    List<Question> findByUser(User user);
    List<Question> findTop10ByOrderByCreatedAtDesc();
}
