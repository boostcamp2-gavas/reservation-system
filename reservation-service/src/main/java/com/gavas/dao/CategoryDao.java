package com.gavas.dao;

import com.gavas.domain.Category;
import com.gavas.exception.EmptyQueryResultException;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.gavas.dao.sqls.CategorySqls.SELECT_CATEGORY_BY_ID;
import static com.gavas.dao.sqls.CategorySqls.SELECT_CATEGORY_LIST;

@Repository
@PropertySource("classpath:/application.properties")
public class CategoryDao {
    private NamedParameterJdbcTemplate jdbc;
    private RowMapper<Category> rowMapper = BeanPropertyRowMapper.newInstance(Category.class);

    public CategoryDao(DataSource dataSource) {
        this.jdbc = new NamedParameterJdbcTemplate(dataSource);
    }

    public List<Category> selectCategoryList() {
        return jdbc.query(SELECT_CATEGORY_LIST, rowMapper);
    }

    public Category findCategoryById(Long categoryId) {
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("id", categoryId);
        try {
            return jdbc.queryForObject(SELECT_CATEGORY_BY_ID, paramMap, rowMapper);
        } catch (EmptyResultDataAccessException exception) {
            throw new EmptyQueryResultException("Category Id");
        }
    }
}
