package com.stackoverflow.clone.repository;


import com.stackoverflow.clone.entity.Tag;
import com.stackoverflow.clone.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface UserRepository extends JpaRepository<User, Long> {

    User findByUsername(String username);

    boolean existsByUsername(String username);

    @Query(value = "SELECT u.id, u.username, t.id, COUNT(t.id) AS tag_count " +
            "FROM users u " +
            "LEFT JOIN questions q ON u.id = q.user_id " + // Updated table name to 'questions'
            "LEFT JOIN question_tags qt ON q.id = qt.question_id " +
            "LEFT JOIN tags t ON qt.tag_id = t.id " +
            "GROUP BY u.id, t.id " +
            "ORDER BY u.id, tag_count DESC " +
            "LIMIT 3",
            nativeQuery = true)
    List<Object[]> findTop3TagsForEachUser();

    @Query("SELECT t FROM User u " +
            "JOIN u.questions q " +
            "JOIN q.tags t " +
            "WHERE u.id = :userId " +
            "GROUP BY t " +
            "ORDER BY COUNT(t) DESC " +
            "limit 3")
    List<Tag> findTop3TagsByUserId(@Param("userId") Long userId);

}
