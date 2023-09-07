package com.stackoverflow.clone.service;

import com.stackoverflow.clone.entity.Tag;

import java.util.Set;

public interface TagService {
    Set<Tag> findOrCreateTag(String tagInput);

    void deleteTagIfNotUsed(Tag tag);
}
