package com.devtiro.blog.services;

import com.devtiro.blog.entity.Post;

import java.util.List;
import java.util.UUID;

public interface PostService {
    List<Post>  getAllPosts(UUID categoryId, UUID tagId);


}
