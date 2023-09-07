package com.stackoverflow.clone.repository;

import com.stackoverflow.clone.entity.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface TagRepository extends JpaRepository<Tag,Long> {
    Optional<Tag> findByName(String tagName);

}
