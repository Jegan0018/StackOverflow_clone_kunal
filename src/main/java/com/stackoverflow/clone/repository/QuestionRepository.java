package com.stackoverflow.clone.repository;

import com.stackoverflow.clone.entity.Question;
import com.stackoverflow.clone.entity.Tag;
import com.stackoverflow.clone.entity.User;
import com.stackoverflow.clone.entity.Vote;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface QuestionRepository extends JpaRepository<Question,Long> {
    int countQuestionsByTags(Tag tag);
    List<Question> findByUser(User user);
    List<Question> findTop10ByOrderByCreatedAtDesc();
    @Query("SELECT q FROM Question q WHERE q.title LIKE %:search% OR q.problem LIKE %:search%")
    List<Question> findQuestionsBySearch(@Param("search") String search);

    Page<Question> findAllByOrderByCreatedAtDesc(Pageable pageable);

    List<Question> findAllByUserName(String username);

}
