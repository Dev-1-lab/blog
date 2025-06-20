package com.devtiro.blog.mappers;

import com.devtiro.blog.dto.PostDTO;
import com.devtiro.blog.entity.Post;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface PostMapper {

    @Mapping(target = "author",source = "author")
    @Mapping(target = "tags",source = "tags")
    @Mapping(target = "category",source = "category")
    PostDTO toDTO(Post post);



}
