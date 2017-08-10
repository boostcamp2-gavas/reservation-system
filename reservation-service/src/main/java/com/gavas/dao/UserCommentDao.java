package com.gavas.dao;

import com.gavas.dao.sqls.UserCommentSqls;
import com.gavas.domain.Category;
import com.gavas.domain.dto.TotalCommentStatusDto;
import com.gavas.exception.EmptyQueryResultException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Repository
public class UserCommentDao {
    private NamedParameterJdbcTemplate jdbc;
    private RowMapper<TotalCommentStatusDto> totalCommentStatusDtoRowMapper = BeanPropertyRowMapper.newInstance(TotalCommentStatusDto.class);

    public UserCommentDao(DataSource dataSource) {
        this.jdbc = new NamedParameterJdbcTemplate(dataSource);
    }

    public TotalCommentStatusDto selectTotalCommentStatusByProductId(Long productId) {
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("productId", productId);
        try {
            return jdbc.queryForObject(UserCommentSqls.SELECT_TOTAL_COMMENT_STATUS_BY_PRODUCT_ID, paramMap, totalCommentStatusDtoRowMapper);
        } catch (EmptyResultDataAccessException exception) {
            throw new EmptyQueryResultException("Product Comment Total Score");
        }
    }

}
