package com.devtiro.blog.services;


import com.devtiro.blog.entity.Category;

import java.util.List;

public interface CategoryService {

    List<Category> listCategories();

    Category createCategory(Category category);
}
