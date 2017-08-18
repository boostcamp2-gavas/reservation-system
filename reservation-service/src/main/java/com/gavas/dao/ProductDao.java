package com.gavas.dao;

import com.gavas.domain.dto.ProductDetailsDto;
import com.gavas.domain.dto.ProductDto;
import com.gavas.domain.dto.ProductPriceInfoDto;
import com.gavas.domain.dto.ProductReserveDto;
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

import static com.gavas.dao.sqls.ProductSqls.*;

@Repository
@PropertySource("classpath:/application.properties")
public class ProductDao {

    private NamedParameterJdbcTemplate jdbc;
    private RowMapper<Long> productCountRowMapper = BeanPropertyRowMapper.newInstance(Long.class);
    private RowMapper<ProductDto> productDtoRowMapper = BeanPropertyRowMapper.newInstance(ProductDto.class);
    private RowMapper<ProductDetailsDto> productDetailsDtoRowMapper = BeanPropertyRowMapper.newInstance(ProductDetailsDto.class);
    private RowMapper<ProductReserveDto> productReserveDtoRowMapper = BeanPropertyRowMapper.newInstance(ProductReserveDto.class);
    private RowMapper<ProductPriceInfoDto> productPriceInfoDtoRowMapper = BeanPropertyRowMapper.newInstance(ProductPriceInfoDto.class);

    public ProductDao(DataSource dataSource) {
        this.jdbc = new NamedParameterJdbcTemplate(dataSource);
    }

    public Long findProudctId(Long productId) {
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("productId", productId);
        try {
            Long l = jdbc.queryForObject(SELECT_PRODUCT_ID_BY_ID, paramMap, Long.class);
            return l;
        } catch (EmptyResultDataAccessException exception) {
            throw new EmptyQueryResultException("Product id : " + productId);
        }
    }

    public List<ProductDto> selectProductList(Long offsetId){
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("offsetId", offsetId);
        try {
            return jdbc.query(SELECT_PRODUCT_LIST,paramMap,productDtoRowMapper);
        } catch (EmptyResultDataAccessException exception){
            throw new EmptyQueryResultException("전체 Product");
        }
    }

    public Long selectProductCount(){
        try {
            Long l = jdbc.queryForObject(SELECT_PRODUCT_COUNT, new HashMap<>(), Long.class);
            return l;
        } catch (EmptyResultDataAccessException exception){
            throw new EmptyQueryResultException("Category");
        }
    }

    public Long selectProductCountByCategoryId(Long categoryId) {
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("categoryId", categoryId);
        try {
            Long l = jdbc.queryForObject(SELECT_PRODUCT_COUNT_BY_CATEGORY_ID, paramMap, Long.class);
            return l;
        } catch (EmptyResultDataAccessException exception) {
            throw new EmptyQueryResultException(categoryId + "번 Category의 Product");
        }
    }

    public List<ProductDto> selectProductListByCategoryId(Long categoryId, Long offsetId) {
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("categoryId", categoryId);
        paramMap.put("offsetId", offsetId);
        try {
            return jdbc.query(SELECT_PRODUCT_LIST_BY_CATEGORY_ID, paramMap, productDtoRowMapper);
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

    public String selectProductNameByProductId(Long productId) {
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("productId", productId);
        try {
            return jdbc.queryForObject(SELECT_PRODUCT_NAME_BY_PRODUCT_ID, paramMap, String.class);
        } catch (EmptyResultDataAccessException exception) {
            throw new EmptyQueryResultException("Product Detail");
        }
    }

    public ProductReserveDto selectProductReserveInfoByProductId(Long productId) {
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("productId", productId);
        try {
                return jdbc.queryForObject(SELECT_PRODUCT_RESERVE_INFO_BY_PRODUCT_ID, paramMap, productReserveDtoRowMapper);
            } catch (EmptyResultDataAccessException exception) {
                throw new EmptyQueryResultException("Product Reserve");
            }
    }

    public List<ProductPriceInfoDto> selectProductPriceInfoByProductId(Long productId) {
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("productId", productId);
        return jdbc.query(SELECT_PRODUCT_PRICE_INFO_BY_PRODUCT_ID, paramMap, productPriceInfoDtoRowMapper);
    }
}
