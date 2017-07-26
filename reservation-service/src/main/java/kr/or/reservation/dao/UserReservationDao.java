package kr.or.reservation.dao;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import kr.or.reservation.dto.CommentDTO;
import kr.or.reservation.dto.ReservationDTO;
import kr.or.reservation.dto.UserReservationDTO;
import kr.or.reservation.sqls.CommentSqls;
import kr.or.reservation.sqls.ReservationSqls;
import kr.or.reservation.sqls.UserReservationSqls;

@Repository
public class UserReservationDao {

	private NamedParameterJdbcTemplate jdbc;
	private RowMapper<UserReservationDTO> mapper = BeanPropertyRowMapper.newInstance(UserReservationDTO.class);
	
	public UserReservationDao(DataSource dataSource) {
		this.jdbc = new NamedParameterJdbcTemplate(dataSource);
	}

	public List<UserReservationDTO> selectAll(int userId) {
		Map<String , ?> map = Collections.singletonMap("id",userId);
		return jdbc.query(UserReservationSqls.selectAll,map,mapper);
	}
}
