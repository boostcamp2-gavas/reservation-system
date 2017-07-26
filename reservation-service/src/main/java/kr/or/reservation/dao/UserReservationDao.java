package kr.or.reservation.dao;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.apache.log4j.Logger;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import kr.or.reservation.dto.CommentDTO;
import kr.or.reservation.dto.ReservationDTO;
import kr.or.reservation.dto.UserReservationDTO;
import kr.or.reservation.sqls.CommentSqls;
import kr.or.reservation.sqls.ReservationSqls;
import kr.or.reservation.sqls.UserReservationSqls;

@Repository
public class UserReservationDao {

	Logger log = Logger.getLogger(this.getClass());
	private NamedParameterJdbcTemplate jdbc;
	private RowMapper<UserReservationDTO> mapper = BeanPropertyRowMapper.newInstance(UserReservationDTO.class);
	
	public UserReservationDao(DataSource dataSource) {
		this.jdbc = new NamedParameterJdbcTemplate(dataSource);
	}

	public List<UserReservationDTO> selectAll(int userId) {
		Map<String , ?> map = Collections.singletonMap("id",userId);
		return jdbc.query(UserReservationSqls.SELECTAll,map,mapper);
	}
	
	public boolean cancelReservation(int userId,int reservationId) {
		Map<String , Integer> map = new HashMap<>();
		map.put("id", reservationId);
		map.put("userId", userId);
		return jdbc.update(UserReservationSqls.CANCEL_RESERVATION_ONE,map)==1;
	}
}
