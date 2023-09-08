package com.stackoverflow.clone.service;

import com.stackoverflow.clone.entity.Question;
import com.stackoverflow.clone.entity.Tag;

import java.util.List;
import java.util.Set;

public interface TagService {
    Set<Tag> findOrCreateTag(String tagInput);

    void deleteTagIfNotUsed(Tag tag);

    List<Question> findQuestionsByTagName(String tagName);

    List<Tag> findAll();

    List<Tag> findAllByCreatedAtDesc();
}
