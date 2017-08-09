package com.gavas.dao;

import com.gavas.dao.sqls.ProductSqls;
import com.gavas.domain.dto.ProductDetailsDto;
import com.gavas.domain.dto.ProductDto;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.gavas.dao.sqls.ProductSqls.SELECT_PRODUCT_DETAIL_BY_PRODUCT_ID;

@Repository
@PropertySource("classpath:/application.properties")
public class ProductDao {

    private NamedParameterJdbcTemplate jdbc;
    private RowMapper<ProductDto> productDtoRowMapper = BeanPropertyRowMapper.newInstance(ProductDto.class);
    private RowMapper<ProductDetailsDto> productDetailsDtoRowMapper = BeanPropertyRowMapper.newInstance(ProductDetailsDto.class);

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
        return jdbc.query(ProductSqls.SELECT_PRODUCT_LIST_BY_CATGORYID,paramMap, productDtoRowMapper);
    }

    public ProductDetailsDto selectProductDetailsByProductId(Long productId) {
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("productId", productId);
        return jdbc.queryForObject(SELECT_PRODUCT_DETAIL_BY_PRODUCT_ID, paramMap, productDetailsDtoRowMapper);
    }
}
