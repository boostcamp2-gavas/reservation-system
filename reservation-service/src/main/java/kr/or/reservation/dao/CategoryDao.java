package kr.or.reservation.dao;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
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
import kr.or.reservation.sqls.CategorySqls;

@Repository
public class CategoryDao {
	private NamedParameterJdbcTemplate jdbc;
	private SimpleJdbcInsert insertAction;
	private RowMapper<Category> rowMapper = BeanPropertyRowMapper.newInstance(Category.class);
	
	public CategoryDao(DataSource dataSource) {
		this.jdbc = new NamedParameterJdbcTemplate(dataSource); 
		this.insertAction = new SimpleJdbcInsert(dataSource) 
				.withTableName("category") 
				.usingGeneratedKeyColumns("id"); 
	}
	
	public Long insert(Category category) {
		SqlParameterSource params = new BeanPropertySqlParameterSource(category);
		return insertAction.executeAndReturnKey(params).longValue();
	}
	
	public Category selectById(long id) {
		Map<String, ?> params = Collections.singletonMap("id", id);
		return jdbc.queryForObject(CategorySqls.SELECT_BY_ID, params, rowMapper);
	}
	
	public List<Category> selectforList() {
		Map<String, ?> params = Collections.emptyMap();
		return jdbc.query(CategorySqls.SELECT_ALL,params,rowMapper);
	}

	public int updateById(Category category) {
		SqlParameterSource params = new BeanPropertySqlParameterSource(category);
		return jdbc.update(CategorySqls.UPDATE_BY_ID, params);
	}

	public int deleteById(long id) {
		Map<String , ?> map = Collections.singletonMap("id",id);
		return jdbc.update(CategorySqls.DELETE_BY_ID, map);
	}
	

	
}
