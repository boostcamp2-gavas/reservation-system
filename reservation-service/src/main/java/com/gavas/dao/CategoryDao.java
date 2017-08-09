package com.gavas.dao;

import com.gavas.dao.sqls.CategorySqls;
import com.gavas.domain.Category;
import com.gavas.domain.dto.ProductDto;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;

@Repository
@PropertySource("classpath:/application.properties")
public class CategoryDao {

    private NamedParameterJdbcTemplate jdbc;
    private RowMapper<Category> rowMapper = BeanPropertyRowMapper.newInstance(Category.class);

    public CategoryDao(DataSource dataSource) {
        this.jdbc = new NamedParameterJdbcTemplate(dataSource);
    }

    public List<Category> selectCategoryList(){

        return jdbc.query(CategorySqls.SELECT_CATEGORY_LIST,rowMapper);

    }
}
