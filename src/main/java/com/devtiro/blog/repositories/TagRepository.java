package com.devtiro.blog.repositories;

import com.devtiro.blog.entity.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;
import java.util.UUID;

@Repository
public interface TagRepository extends JpaRepository<Tag, UUID> {

    @Query("select t from Tag t left join t.posts")
    Set<Tag> findAllWithPostCount();


    List<Tag> findByNameIn(Set<String> names);
}
