package com.gavas.dao;

import com.gavas.domain.Category;
import com.gavas.domain.User;
import com.gavas.exception.EmptyQueryResultException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

import static com.gavas.dao.sqls.UserSqls.SELECT_USER_BY_SNS_ID;

@Repository
public class UserDao {
    private NamedParameterJdbcTemplate jdbc;
    private RowMapper<User> userRowMapper = BeanPropertyRowMapper.newInstance(User.class);
    private SimpleJdbcInsert simpleJdbcInsert;

    public UserDao(DataSource dataSource) {
        this.jdbc = new NamedParameterJdbcTemplate(dataSource);
        this.simpleJdbcInsert = new SimpleJdbcInsert(dataSource)
                .withTableName("users")
                .usingGeneratedKeyColumns("id");
    }


    public User selectUserBySnsId(String snsId) {
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("snsId", snsId);
        try {
            return jdbc.queryForObject(SELECT_USER_BY_SNS_ID, paramMap, userRowMapper);
        } catch (EmptyResultDataAccessException exceptionΩ) {
            return null;
        }
    }

    public Long insertUser(User user) {
        SqlParameterSource params = new BeanPropertySqlParameterSource(user);
        return simpleJdbcInsert.executeAndReturnKey(params).longValue();
    }
}
