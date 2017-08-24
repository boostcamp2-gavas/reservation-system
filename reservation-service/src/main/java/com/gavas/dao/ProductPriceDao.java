package com.gavas.dao;

import com.gavas.domain.ProductPrice;
import com.gavas.exception.EmptyQueryResultException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.gavas.dao.sqls.ProductPriceSqls.SELECT_PRODUCT_PRICE_BY_PRODUCT_ID;

@Repository
public class ProductPriceDao {
    private NamedParameterJdbcTemplate jdbc;
    private RowMapper<ProductPrice> productPriceRowMapper;

    @Autowired
    public ProductPriceDao(DataSource dataSource) {
        this.jdbc = new NamedParameterJdbcTemplate(dataSource);
        this.productPriceRowMapper = BeanPropertyRowMapper.newInstance(ProductPrice.class);
    }

    public List<ProductPrice> selectProductPriceByProductId(Long id) {
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("id", id);
        List<ProductPrice> productPriceList = jdbc.query(SELECT_PRODUCT_PRICE_BY_PRODUCT_ID, paramMap, productPriceRowMapper);
        if(productPriceList.isEmpty()) {
            throw new EmptyQueryResultException(id + "번의 Product price");
        } else {
            return productPriceList;
        }

    }
}
