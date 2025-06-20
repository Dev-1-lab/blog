package com.devtiro.blog.services.impl;

import com.devtiro.blog.entity.Tag;
import com.devtiro.blog.repositories.TagRepository;
import com.devtiro.blog.services.TagService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class TagServiceImpl implements TagService {
    private final TagRepository tagRepository;


    @Override
    public Set<Tag> getTags() {
        return tagRepository.findAllWithPostCount();
    }

    @Override
    @Transactional
    public List<Tag> createTags(Set<String> names) {
        List<Tag> existingTagNames = tagRepository.findByNameIn(names);

        Set<String> existingNames = existingTagNames
                .stream()
                .map(Tag::getName)
                .collect(Collectors.toSet());
        List<Tag> newTags = names
                .stream()
                .filter(name -> !existingNames.contains(name))
                .map(name -> Tag.builder().name(name).posts(new HashSet<>()).build()).collect(Collectors.toList());
        List<Tag> savedTags = new ArrayList<>();
        if (!newTags.isEmpty()) {
            savedTags = tagRepository.saveAll(newTags);
        }

        return savedTags;
    }

    @Override
    @Transactional
    public void deleteTags(UUID id) {
        tagRepository.findById(id).ifPresent(tag -> {
            if (!tag.getPosts().isEmpty()) {
                throw new IllegalArgumentException("Tags can't be deleted, cause they have posts related to them ");
            }
            tagRepository.deleteById(id);
        });
    }
}
