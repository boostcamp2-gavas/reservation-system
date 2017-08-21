package com.gavas.service.serviceImpl;

import com.gavas.dao.CategoryDao;
import com.gavas.domain.Category;
import com.gavas.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {
    private CategoryDao categoryDao;

    @Autowired
    public CategoryServiceImpl (CategoryDao categoryDao) {
        this.categoryDao = categoryDao;
    }

    @Transactional(readOnly = true)
    @Override
    public Category findCategoryById(Long categoryId) {
        return categoryDao.findCategoryById(categoryId);
    }

    @Transactional(readOnly = true)
    @Override
    public List<Category> getCategoryList() {
        return categoryDao.selectCategoryList();
    }
}
