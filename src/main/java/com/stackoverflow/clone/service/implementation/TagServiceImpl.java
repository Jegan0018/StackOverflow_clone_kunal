package com.stackoverflow.clone.service.implementation;

import com.stackoverflow.clone.entity.Question;
import com.stackoverflow.clone.entity.Tag;
import com.stackoverflow.clone.repository.TagRepository;
import com.stackoverflow.clone.service.TagService;
import org.springframework.stereotype.Service;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
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
                    tag.setName(tagName.toLowerCase());
                    tagRepository.save(tag);
                }
                tags.add(tag);
            }
        }
        return tags;
    }

    @Override
    public void deleteTagIfNotUsed(Tag tag) {
        if (!tag.getPosts().isEmpty()) {
            return;
        }
        tagRepository.delete(tag);
    }

    @Override
    public List<Question> findQuestionsByTagName(String tagName) {
        return tagRepository.findQuestionsByTagId(tagName);
    }

    @Override
    public List<Tag> findAll() {
        return tagRepository.findAll();
    }

    @Override
    public List<Tag> findAllByCreatedAtDesc() {
        return tagRepository.findAllTagsByCreatedAtDesc();
    }

}