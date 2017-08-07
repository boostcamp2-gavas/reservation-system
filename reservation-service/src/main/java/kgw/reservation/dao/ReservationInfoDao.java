package kgw.reservation.dao;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import kgw.reservation.domain.ReservationInfo;
import kgw.reservation.dto.MyReservation;
import kgw.reservation.sql.ReservationInfoSqls;

@Repository
public class ReservationInfoDao {
	private NamedParameterJdbcTemplate jdbc;
	private RowMapper<ReservationInfo> rowMapper = BeanPropertyRowMapper.newInstance(ReservationInfo.class);
	
	private DateFormat df = new SimpleDateFormat("yyyy.M.d.(E)");
	private RowMapper<MyReservation> myReservationRowMapper = (rs, i) -> {
			MyReservation myReservation = new MyReservation();
			myReservation.setId(rs.getInt("id"));
			myReservation.setProductId(rs.getInt("productId"));
			myReservation.setUserId(rs.getInt("userId"));
			myReservation.setGeneralTicketCount(rs.getInt("generalTicketCount"));
			myReservation.setYouthTicketCount(rs.getInt("youthTicketCount"));
			myReservation.setChildTicketCount(rs.getInt("childTicketCount"));
			myReservation.setReservationType(rs.getInt("reservationType"));
			myReservation.setReservationDate(df.format(rs.getDate("reservationDate")));
			myReservation.setName(rs.getString("name"));
			return myReservation;
	};
	private final Logger log = LoggerFactory.getLogger(ReservationInfoDao.class);
	private SimpleJdbcInsert insertAction;
	
	@Autowired
	public ReservationInfoDao(DataSource dataSource) {
		this.jdbc = new NamedParameterJdbcTemplate(dataSource);
		this.insertAction = new SimpleJdbcInsert(dataSource)
				.withTableName("reservation_info")
				.usingGeneratedKeyColumns("id");
	}
	
	public Integer insert(ReservationInfo reservationInfo) {
        SqlParameterSource params = new BeanPropertySqlParameterSource(reservationInfo);
        return insertAction.executeAndReturnKey(params).intValue();
    }
	
	public List<MyReservation> selectByUserId(Integer userId) {
		Map<String, Object> params = Collections.singletonMap("userId", userId);
		try {
			return jdbc.query(ReservationInfoSqls.SELECT_ALL_RESERVATION_BY_USER_ID, params, myReservationRowMapper);
		} catch(DataAccessException e) {
			log.error("ReservationInfoDao::selectByUserId",e);
			return null;
		}
	}
	
	public List<MyReservation> selectByUserIdAndType(Integer userId, Integer type) {
		Map<String, Object> params = new HashMap<>();
		params.put("userId", userId);
		params.put("type", type);
		try {
			return jdbc.query(ReservationInfoSqls.SELECT_RESERVATION_BY_USER_ID_AND_TYPE, params, myReservationRowMapper);
		} catch(DataAccessException e) {
			log.error("ReservationInfoDao::selectByUserIdAndType",e);
			return null;
		}
	}
	
	public Integer deleteByType(Integer type) {
		Map<String, Object> params = Collections.singletonMap("type", type);
		return jdbc.update(ReservationInfoSqls.DELETE_RESERVATION_BY_TYPE, params);
	}
	
	public Integer updateTypeById(Integer id, Integer type) {
		Map<String, Object> params = new HashMap<>();
		params.put("type", type);
		params.put("id", id);
		return jdbc.update(ReservationInfoSqls.UPDATE_TYPE_BY_ID, params);
	}
	public Integer selectCountByUserIdAndProductId(Integer productId, Integer userId) {
		Map<String, Object> params = new HashMap<>();
		params.put("productId", productId);
		params.put("userId", userId);
		return jdbc.queryForObject(ReservationInfoSqls.SELECT_COUNT_RESERVATION_BY_USER_ID_AND_PRODUCT_ID, 
				params, Integer.class);
	}
	public ReservationInfo selectById(Integer id) {
		Map<String, Object> params = Collections.singletonMap("id", id);
		try {
			return jdbc.queryForObject(ReservationInfoSqls.SELECT_RESERVATION_INFO_BY_ID, params, rowMapper);
		} catch(DataAccessException e) {
			log.error("ReservationInfoDao::selectById",e);
			return null;
		}
	}
	
}
