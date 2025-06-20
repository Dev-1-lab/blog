package com.devtiro.blog.controllers;

import com.devtiro.blog.dto.CreateTagRequest;
import com.devtiro.blog.dto.TagResponse;
import com.devtiro.blog.entity.Tag;
import com.devtiro.blog.mappers.TagMapper;
import com.devtiro.blog.services.TagService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api/v1/tags")
public class TagController {
    private final TagService tagService;
    private final TagMapper tagMapper;


    @GetMapping
    public ResponseEntity<List<TagResponse>> getAllTags() {
        Set<Tag> tags = tagService.getTags();
        List<TagResponse> tagResponseList = tags
                .stream()
                .map(tagMapper::toResponse).toList();
        return ResponseEntity.ok(tagResponseList);
    }


    @PostMapping
    public ResponseEntity<List<TagResponse>> addTags(@RequestBody @Valid CreateTagRequest createTagRequest) {

        List<Tag> tags = tagService.createTags(createTagRequest.getNames());
        List<TagResponse> list = tags
                .stream()
                .map(tagMapper::toResponse)
                .toList();

        return new ResponseEntity<>(list, HttpStatus.CREATED);
    }


    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> deleteTags(@PathVariable UUID id) {
        tagService.deleteTags(id);
        return ResponseEntity.noContent().build();
    }


}