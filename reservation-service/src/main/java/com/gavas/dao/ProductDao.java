package com.gavas.dao;

import com.gavas.dao.sqls.ProductSqls;
import com.gavas.domain.dto.ProductDetailsDto;
import com.gavas.domain.dto.ProductDto;
import com.gavas.exception.EmptyQueryResultException;
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

    public Long findProudctId(Long productId) {
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("productId", productId);
        try {
            Long l = jdbc.queryForObject(ProductSqls.SELECT_PRODUCT_ID_BY_ID, paramMap, Long.class);
            return l;
        }catch (EmptyResultDataAccessException exception){
            throw new EmptyQueryResultException("Product id : "+productId);
        }
    }

    public Long selectProductCountByCategoryId(Long categoryId) {
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("categoryId", categoryId);
        try {
            Long l = jdbc.queryForObject(ProductSqls.SELECT_PRODUCT_COUNT, paramMap, Long.class);
            return l;
        } catch (EmptyResultDataAccessException exception){
            throw new EmptyQueryResultException(categoryId+"번 Category의 Product");
        }
    }

    public List<ProductDto> selectProductListByCategoryId(Long categoryId) {
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("categoryId", categoryId);
        try {
            return jdbc.query(ProductSqls.SELECT_PRODUCT_LIST_BY_CATGORYID, paramMap, productDtoRowMapper);
        } catch (EmptyResultDataAccessException exception) {
            throw new EmptyQueryResultException("Product List");
        }
    }

    public ProductDetailsDto selectProductDetailsByProductId(Long productId) {
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("productId", productId);
        try {
            return jdbc.queryForObject(SELECT_PRODUCT_DETAIL_BY_PRODUCT_ID, paramMap, productDetailsDtoRowMapper);
        } catch (EmptyResultDataAccessException exception) {
            throw new EmptyQueryResultException("Product Detail");
        }
    }
}
