package kr.or.reservation.dao;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import kr.or.reservation.domain.Product;
import kr.or.reservation.dto.ProductDetailDTO;
import kr.or.reservation.sqls.ProductSqls;

@Repository
public class ProductDao {

	private NamedParameterJdbcTemplate jdbc;
	private RowMapper<Product> rowMapper = BeanPropertyRowMapper.newInstance(Product.class);
	private RowMapper<ProductDetailDTO> detailMapper = BeanPropertyRowMapper.newInstance(ProductDetailDTO.class);

	
	public ProductDao(DataSource dataSource) {
		this.jdbc = new NamedParameterJdbcTemplate(dataSource);
	}

	public List<Product> selectByCategory(int start, int categoryId) {
		SqlParameterSource parameters = new MapSqlParameterSource().addValue("category_id", categoryId)
				.addValue("start", start);
		return jdbc.query(ProductSqls.SELECT_BY_CATEGORY, parameters, rowMapper);
	}

	public List<Product> selectAll(int start) {
		SqlParameterSource parameters = new MapSqlParameterSource().addValue("start", start);
		return jdbc.query(ProductSqls.SELECT_ALL, parameters, rowMapper);
	}

	public int countCategoryAll() {
		Map<String, ?> params = Collections.emptyMap();
		return jdbc.queryForObject(ProductSqls.SELECT_COUNT_ALL, params, Integer.class);
	}

	public int countCategory(int categoryId) {
		Map<String, ?> params = Collections.singletonMap("category_id", categoryId);
		return jdbc.queryForObject(ProductSqls.SELECT_COUNT_BY_CATEGORYID, params, Integer.class);
	}
	
	public ProductDetailDTO selectOne(int id) {
		Map<String , ?> params = Collections.singletonMap("id", id);
		return jdbc.queryForObject(ProductSqls.SELECT_DETAIL_INFO,params, detailMapper);
	}
	

}
