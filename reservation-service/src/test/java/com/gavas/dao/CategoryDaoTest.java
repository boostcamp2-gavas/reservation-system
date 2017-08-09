package com.gavas.dao;

import com.gavas.config.RootApplicationContextConfig;
import com.gavas.domain.Category;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = RootApplicationContextConfig.class)
@Transactional
public class CategoryDaoTest {
    @Autowired
    CategoryDao categoryDao;

    @Test
    public void shouldCategoryRead() {
        List<Category> categoryList = categoryDao.selectCategoryList();

        Assert.assertNotNull(categoryList);
    }
}
