package com.devtiro.blog.services;

import com.devtiro.blog.entity.Tag;

import java.util.List;
import java.util.Set;
import java.util.UUID;

public interface TagService {

    Set<Tag> getTags();
    List<Tag> createTags(Set<String> names);

    void deleteTags(UUID id);
}
