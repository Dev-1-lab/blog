package com.devtiro.blog.services.impl;

import com.devtiro.blog.entity.Category;
import com.devtiro.blog.entity.Post;
import com.devtiro.blog.entity.PostStatusEnum;
import com.devtiro.blog.entity.Tag;
import com.devtiro.blog.repositories.PostRepository;
import com.devtiro.blog.services.CategoryService;
import com.devtiro.blog.services.PostService;
import com.devtiro.blog.services.TagService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;
    private final CategoryService categoryService;
    private final TagService tagService;

    @Override
    @Transactional
    public List<Post> getAllPosts(UUID categoryId, UUID tagId) {
        if (categoryId != null && tagId != null) {
            Category category = categoryService.getCategoryById(categoryId);
            Tag tag = tagService.getTagById(tagId);
            return postRepository.findAllByStatusAndCategoryAndTagsContaining(
                    PostStatusEnum.PUBLISHED,
                    category,
                    tag);
        }
        if (categoryId != null) {
            Category category = categoryService.getCategoryById(categoryId);
            return postRepository.findAllByStatusAndCategory(PostStatusEnum.PUBLISHED, category);
        }
        if (tagId != null) {
            Tag tag = tagService.getTagById(tagId);
            return postRepository.findAllByStatusAndTagsContaining(PostStatusEnum.PUBLISHED, tag);
        }
        return postRepository.findAllByStatus(PostStatusEnum.PUBLISHED);
    }


}
