package kr.or.reservation.dao;

import java.util.Collections;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import kr.or.reservation.dto.ProductDetailDTO;
import kr.or.reservation.sql.ProductSqls;

@Repository
public class ProductForDetailDao {
	
	private NamedParameterJdbcTemplate jdbc;
	private RowMapper<ProductDetailDTO> rowMapper = BeanPropertyRowMapper.newInstance(ProductDetailDTO.class);

	public ProductForDetailDao(DataSource dataSource) {
		this.jdbc = new NamedParameterJdbcTemplate(dataSource);
	}
	
	public ProductDetailDTO selectOne(int id) {
		Map<String , ?> params = Collections.singletonMap("id", id);
		return jdbc.queryForObject(ProductSqls.SELECT_DETAIL_INFO,params, rowMapper);
	}
	
	
}
