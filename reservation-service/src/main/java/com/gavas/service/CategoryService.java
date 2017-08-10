package com.gavas.service;

import com.gavas.domain.Category;

import java.util.List;

public interface CategoryService {
    Category findCategoryById(Long categoryId);
    List<Category> getCategoryList();
}
