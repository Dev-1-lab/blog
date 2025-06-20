package com.devtiro.blog.services;

import com.devtiro.blog.entity.Tag;

import java.util.List;
import java.util.Set;

public interface TagService {

    Set<Tag> getTags();
    List<Tag> createTags(Set<String> names);
}
