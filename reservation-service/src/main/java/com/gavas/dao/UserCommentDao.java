package com.gavas.dao;

import com.gavas.domain.dto.TotalCommentStatusDto;
import com.gavas.domain.dto.UserCommentDto;
import com.gavas.exception.EmptyQueryResultException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.gavas.dao.sqls.UserCommentSqls.*;

@Repository
public class UserCommentDao {
    private NamedParameterJdbcTemplate jdbc;
    private RowMapper<TotalCommentStatusDto> totalCommentStatusDtoRowMapper = BeanPropertyRowMapper.newInstance(TotalCommentStatusDto.class);
    private RowMapper<UserCommentDto> UserCommentDtoRowMapper = BeanPropertyRowMapper.newInstance(UserCommentDto.class);

    public UserCommentDao(DataSource dataSource) {
        this.jdbc = new NamedParameterJdbcTemplate(dataSource);
    }

    public Long findUserCommentId(Long userCommentId) {
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("userCommentId", userCommentId);
        try {
            return jdbc.queryForObject(SELECT_USER_COMMENT_ID_BY_ID, paramMap, Long.class);
        } catch (EmptyResultDataAccessException exception) {
            throw new EmptyQueryResultException("userCommentId");
        }
    }

    public TotalCommentStatusDto selectTotalCommentStatusByProductId(Long productId) {
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("productId", productId);
        try {
            return jdbc.queryForObject(SELECT_TOTAL_COMMENTS_STATUS_BY_PRODUCT_ID, paramMap, totalCommentStatusDtoRowMapper);
        } catch (EmptyResultDataAccessException exception) {
            throw new EmptyQueryResultException("Product Comment Total Score");
        }
    }

    public List<UserCommentDto> selectUserCommentByProductId(Long productId, Long commentId, Integer limit) {
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("productId", productId);
        paramMap.put("commentId", commentId);
        paramMap.put("limit", limit);
        List<UserCommentDto> userCommentDtoList = jdbc.query(SELECT_USER_COMMENTS_BY_PRODUCT_ID, paramMap, UserCommentDtoRowMapper);
        if (userCommentDtoList.isEmpty()) {
            throw new EmptyQueryResultException(productId + "번 Product의 UserComment");
        } else {
            return userCommentDtoList;
        }
    }

    public List<Long> selectFileIdByUserCommentId(Long userCommentId) {
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("userCommentId", userCommentId);
        List<Long> fileIdList = jdbc.queryForList(SELECT_FILE_ID_BY_USER_COMMENT_ID, paramMap, Long.class);
        if (fileIdList.isEmpty()) {
            throw new EmptyQueryResultException(userCommentId + "번 Product의 UserComment");
        } else {
            return fileIdList;
        }
    }

}
