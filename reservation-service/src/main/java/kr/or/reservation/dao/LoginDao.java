package kr.or.reservation.dao;

import java.sql.Timestamp;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import kr.or.reservation.domain.Category;
import kr.or.reservation.dto.NaverUserDTO;
import kr.or.reservation.sql.CategorySqls;
import kr.or.reservation.sql.LoginSqls;

@Repository
public class LoginDao {
	private NamedParameterJdbcTemplate jdbc;
	private SimpleJdbcInsert insertAction;
	private RowMapper<NaverUserDTO> rowMapper = BeanPropertyRowMapper.newInstance(NaverUserDTO.class);

	public LoginDao(DataSource dataSource) {
		this.jdbc = new NamedParameterJdbcTemplate(dataSource);
		this.insertAction = new SimpleJdbcInsert(dataSource).withTableName("users").usingGeneratedKeyColumns("id");

	}

	public Long insert(NaverUserDTO dto) {
		SqlParameterSource params = new BeanPropertySqlParameterSource(dto);
		return insertAction.executeAndReturnKey(params).longValue();
	}

	public boolean isEmpty(int snsId) {
		Map<String, ?> params = Collections.singletonMap("id", snsId);
		return jdbc.queryForObject(LoginSqls.SELECTBYID, params, Integer.class) == 0;
	}

}
