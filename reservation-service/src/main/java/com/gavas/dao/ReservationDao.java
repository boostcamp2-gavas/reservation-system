package com.gavas.dao;

import com.gavas.domain.Reservation;
import com.gavas.domain.dto.ReservationDto;
import com.gavas.exception.DataAccessFailException;
import com.gavas.exception.EmptyQueryResultException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.gavas.dao.sqls.ReservationSqls.SELECT_RESERVATION_BY_USER_ID;
import static com.gavas.dao.sqls.ReservationSqls.UPDATE_RESERVATION_TYPE_BY_ID;

@Repository
public class ReservationDao {
    private SimpleJdbcInsert simpleJdbcInsert;
    private NamedParameterJdbcTemplate jdbcTemplate;
    private RowMapper<ReservationDto> reservationDtoRowMapper;


    @Autowired
    public ReservationDao(DataSource dataSource) {
        this.jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
        this.simpleJdbcInsert = new SimpleJdbcInsert(dataSource)
                .withTableName("reservation_info")
                .usingGeneratedKeyColumns("id");
        this.reservationDtoRowMapper = BeanPropertyRowMapper.newInstance(ReservationDto.class);
    }

    public List<ReservationDto> selectReservationListByUserId(Long id) {
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("id", id);
        List<ReservationDto> reservationDtoList = jdbcTemplate.query(SELECT_RESERVATION_BY_USER_ID, paramMap, reservationDtoRowMapper);
        if (reservationDtoList.isEmpty()) {
            throw new EmptyQueryResultException(id + "번의 reservation info");
        } else {
            return reservationDtoList;
        }
    }

    public Integer updateReservation(Reservation reservation) {
        SqlParameterSource paramsSource = new BeanPropertySqlParameterSource(reservation);
        try {
            return jdbcTemplate.update(UPDATE_RESERVATION_TYPE_BY_ID, paramsSource);
        } catch (DataAccessException exception) {
            throw new DataAccessFailException(reservation.toString() + "is incorrect data");
        }
    }

    public Integer insertReservation(Reservation reservation) {
        SqlParameterSource paramsSource = new BeanPropertySqlParameterSource(reservation);
        return simpleJdbcInsert.executeAndReturnKey(paramsSource).intValue();
    }
}
