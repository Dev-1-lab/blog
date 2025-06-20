package com.devtiro.blog.controllers;

import com.devtiro.blog.dto.PostDTO;
import com.devtiro.blog.entity.Post;
import com.devtiro.blog.services.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api/v1/posts")
public class PostController {
    private final PostService postService;
    @GetMapping
    public ResponseEntity<List<PostDTO>> getAllPosts(@RequestParam(required = false) UUID categoryId,
                                                     @RequestParam(required = false) UUID tagId) {


        List<Post> posts = postService.getAllPosts(categoryId, tagId);



    }




}
