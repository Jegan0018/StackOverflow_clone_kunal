package com.stackoverflow.clone.service.implementation;

import com.stackoverflow.clone.entity.Tag;
import com.stackoverflow.clone.repository.TagRepository;
import com.stackoverflow.clone.service.TagService;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

public class TagServiceImpl implements TagService {

    private TagRepository tagRepository;

    public TagServiceImpl(TagRepository tagRepository) {
        this.tagRepository = tagRepository;
    }

    @Override
    public Set<Tag> findOrCreateTag(String tagInput) {
        String[] tagNames = tagInput.split(" ");
        Set<Tag> tags = new HashSet<>();
        Tag tag;
        for (String tagName : tagNames) {
            tagName = tagName.trim();
            if (!tagName.isEmpty()) {
                Optional<Tag> existingTag = tagRepository.findByName(tagName);
                if (existingTag.isPresent()) {
                    tag = existingTag.get();
                } else {
                    tag = new Tag();
                    tag.setName(tagName);
                    tagRepository.save(tag);
                }
                tags.add(tag);
            }
        }
        return tags;
    }
}