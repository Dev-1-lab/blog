package com.devtiro.blog.repositories;

import com.devtiro.blog.entity.Category;
import com.devtiro.blog.entity.Post;
import com.devtiro.blog.entity.PostStatusEnum;
import com.devtiro.blog.entity.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;
import java.util.UUID;

@Repository
public interface PostRepository extends JpaRepository<Post, UUID> {
    List<Post> findAllByStatusAndCategoryAndTagsContaining(PostStatusEnum status, Category category, Tag tag);
      List<Post> findAllByStatusAndCategory(PostStatusEnum status, Category category);
    List<Post> findAllByStatusAndTagsContaining(PostStatusEnum status,Tag tag );

    List<Post> findAllByStatus(PostStatusEnum status);

}
