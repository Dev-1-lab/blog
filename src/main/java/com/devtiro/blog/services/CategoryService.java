package com.devtiro.blog.services;


import com.devtiro.blog.entity.Category;

import java.util.List;
import java.util.UUID;

public interface CategoryService {

    List<Category> listCategories();

    Category createCategory(Category category);
    void deleteCategory(UUID id);

    Category getCategoryById(UUID id);
}
