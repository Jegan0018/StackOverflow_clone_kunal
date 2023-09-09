package com.stackoverflow.clone.repository;

import com.stackoverflow.clone.entity.Question;
import com.stackoverflow.clone.entity.Tag;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface TagRepository extends JpaRepository<Tag, Integer> {
    Optional<Tag> findByName(String tagName);

    @Query("SELECT q FROM Question q JOIN q.tags t WHERE t.name = :tagName")
    List<Question> findQuestionsByTagId(@Param("tagName") String tagName);

    @Query("SELECT t FROM Tag t ORDER BY t.createdAt DESC")
    List<Tag> findAllTagsByCreatedAtDesc();

    List<Tag> findAllByOrderByNameAsc();

    @Query("SELECT t FROM Tag t WHERE " +
            "LOWER(t.name) LIKE LOWER(CONCAT('%', :search, '%'))")
    List<Tag> search(@Param("search") String search, Sort sort);
    @Query("SELECT t FROM Tag t WHERE " +
            "LOWER(t.name) LIKE LOWER(CONCAT('%', :search, '%'))")
    List<Tag> search(@Param("search") String search);

}
