package com.devtiro.blog.controllers;

import com.devtiro.blog.dto.CategoryDTO;
import com.devtiro.blog.dto.CreateCategoryRequest;
import com.devtiro.blog.entity.Category;
import com.devtiro.blog.mappers.CategoryMapper;
import com.devtiro.blog.services.CategoryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/v1/categories")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    private final CategoryMapper categoryMapper;
    
    @GetMapping
    public ResponseEntity<List<CategoryDTO>> listCategories() {

        List<Category> categories = categoryService.listCategories();
        List<CategoryDTO>  categoryDTO=categories
                .stream()
                .map(categoryMapper::categoryToCategoryDTO)
                .toList();
        return ResponseEntity.ok(categoryDTO);
    }


    @PostMapping
    public ResponseEntity<CategoryDTO> addCategory(@Valid @RequestBody CreateCategoryRequest createCategoryRequest) {
        Category entity = categoryMapper.toEntity(createCategoryRequest);
        Category savedCategory = categoryService.createCategory(entity);

        return new ResponseEntity<>(categoryMapper.categoryToCategoryDTO(savedCategory), HttpStatus.CREATED);
    }




}
