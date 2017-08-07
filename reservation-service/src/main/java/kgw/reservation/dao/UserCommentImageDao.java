package kgw.reservation.dao;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import kgw.reservation.domain.ReservationUserCommentImage;
import kgw.reservation.sql.UserCommentImageSqls;

@Repository
public class UserCommentImageDao {
	private NamedParameterJdbcTemplate jdbc;

	@Autowired
	public UserCommentImageDao(DataSource dataSource) {
		this.jdbc = new NamedParameterJdbcTemplate(dataSource);
	}
	public void insertBatch(List<ReservationUserCommentImage> reservationUserCommentImageList) {
		List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (ReservationUserCommentImage reservationUserCommentImage : reservationUserCommentImageList) {
			parameters.add(new BeanPropertySqlParameterSource(reservationUserCommentImage));
		}
		this.jdbc.batchUpdate(UserCommentImageSqls.INSERT_BY_COMMENT_AND_FILE_ID_BATCH, parameters.toArray(new SqlParameterSource[0]));
	}
	
	
	
	
}
