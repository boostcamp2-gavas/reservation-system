package kr.or.reservation.dao;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import kr.or.reservation.dto.ReservationDTO;
import kr.or.reservation.sqls.ReservationSqls;

@Repository
public class ReservationDao {

	private NamedParameterJdbcTemplate jdbc;
	private RowMapper<ReservationDTO> reservationMapper = BeanPropertyRowMapper.newInstance(ReservationDTO.class);
	
	public ReservationDao(DataSource dataSource) {
		this.jdbc = new NamedParameterJdbcTemplate(dataSource);
	}

	public ReservationDTO selectOne(int id) {
		Map<String , ?> params = Collections.singletonMap("id", id);
		return jdbc.queryForObject(ReservationSqls.SELECT_ONE,params, reservationMapper);
	}
	
	public List<Map<String,Object>> selectPrice(int id) {
		Map<String , ?> params = Collections.singletonMap("id", id);
		return jdbc.queryForList(ReservationSqls.SELECT_PRICE, params);
	}
	
}
