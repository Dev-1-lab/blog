package com.devtiro.blog.mappers;

import com.devtiro.blog.dto.TagResponse;
import com.devtiro.blog.entity.Post;
import com.devtiro.blog.entity.PostStatusEnum;
import com.devtiro.blog.entity.Tag;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.ReportingPolicy;

import java.util.List;
import java.util.Set;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface TagMapper {
    @Mapping(
            target = "postCount",
            source = "posts",
            qualifiedByName = "calculatePostCount"
    )
    TagResponse toResponse(Tag tag);

    @Named("calculatePostCount")
    default Integer calculatePostCount(Set<Post> postList) {
        if (postList == null || postList.isEmpty()) {
            return 0;
        }
        return (int) postList
                .stream()
                .filter(post -> PostStatusEnum.PUBLISHED.equals(post.getStatus()))
                .count();
    }

}
