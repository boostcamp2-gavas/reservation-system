package kr.or.reservation.dao;

import java.util.Collections;
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
import kr.or.reservation.domain.ReservationInfo;
import kr.or.reservation.dto.ReservationDTO;
import kr.or.reservation.sqls.ReservationSqls;

@Repository
public class ReservationInfoDao {

	private NamedParameterJdbcTemplate jdbc;
	private SimpleJdbcInsert insertAction;
	private RowMapper<ReservationInfo> rowMapper = BeanPropertyRowMapper.newInstance(ReservationInfo.class);
	
	public ReservationInfoDao(DataSource dataSource) {
		this.jdbc = new NamedParameterJdbcTemplate(dataSource); 
		this.insertAction = new SimpleJdbcInsert(dataSource) 
				.withTableName("reservation_info") 
				.usingGeneratedKeyColumns("id"); 
	}
	
	public Long insert(ReservationInfo reservationInfo) {
		SqlParameterSource params = new BeanPropertySqlParameterSource(reservationInfo);
		return insertAction.executeAndReturnKey(params).longValue();
	}
	
	

}
