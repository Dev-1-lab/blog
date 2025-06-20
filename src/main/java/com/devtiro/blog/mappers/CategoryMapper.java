package com.devtiro.blog.mappers;

import com.devtiro.blog.dto.CategoryDTO;
import com.devtiro.blog.dto.CreateCategoryRequest;
import com.devtiro.blog.entity.Category;
import com.devtiro.blog.entity.Post;
import com.devtiro.blog.entity.PostStatusEnum;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CategoryMapper {
    @Mapping(target = "postCount",source = "posts",qualifiedByName = "calculatePostCount")
    CategoryDTO categoryToCategoryDTO(Category category);



    Category toEntity(CreateCategoryRequest createCategoryRequest);


    @Named("calculatePostCount")
    default long calculatePostCount(List<Post> posts) {
        if (null==posts) return 0;
        return posts
                .stream()
                .filter(post-> PostStatusEnum.PUBLISHED.equals(post.getStatus()))
                .count();
    }





}
