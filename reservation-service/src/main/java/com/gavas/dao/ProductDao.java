package com.gavas.dao;

import com.gavas.dao.sqls.ProductSqls;
import com.gavas.domain.dto.ProductDto;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
@PropertySource("classpath:/application.properties")
public class ProductDao {

    private NamedParameterJdbcTemplate jdbc;
    private RowMapper<ProductDto> rowMapper = BeanPropertyRowMapper.newInstance(ProductDto.class);

    public ProductDao(DataSource dataSource) {
        this.jdbc = new NamedParameterJdbcTemplate(dataSource);
    }

    public Long selectProductCountByCategoryId(Long categoryId){
        Map<String,Object> paramMap = new HashMap<>();
        paramMap.put("categoryId",categoryId);
        return jdbc.queryForObject(ProductSqls.SELECT_PRODUCT_COUNT,paramMap,Long.class);
    }

    public List<ProductDto> selectProductListByCategoryId(Long categoryId){
        Map<String,Object> paramMap = new HashMap<>();
        paramMap.put("categoryId",categoryId);
        return jdbc.query(ProductSqls.SELECT_PRODUCT_LIST_BY_CATGORYID,paramMap,rowMapper);
    }
}
