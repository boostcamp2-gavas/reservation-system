package kgw.reservation.dao;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import kgw.reservation.dto.CommentStats;
import kgw.reservation.dto.UserComment;
import kgw.reservation.sql.UserCommentSqls;

@Repository
public class UserCommentDao {
	private NamedParameterJdbcTemplate jdbc;
	private DateFormat df = new SimpleDateFormat("yyyy.M.d.");
	private RowMapper<UserComment> rowMapper = (rs, i) -> {
			UserComment userComment= new UserComment();
			userComment.setId(rs.getInt("id"));
			userComment.setComment(rs.getString("comment"));
			userComment.setCreateDate(rs.getDate("create_date"));
			userComment.setModifyDate(rs.getDate("modify_date"));
			userComment.setScore(rs.getDouble("score"));
			userComment.setUserId(rs.getInt("user_id"));
			userComment.setUsername(rs.getString("username").substring(0, rs.getString("username").length()-3)+"****");
			userComment.setReservationDate(df.format(rs.getDate("reservation_date")));
			return userComment;
	};
	private RowMapper<CommentStats> userCommentStatsRowMapper = BeanPropertyRowMapper.newInstance(CommentStats.class);
	
	@Autowired
    public UserCommentDao(DataSource dataSource) {
		this.jdbc = new NamedParameterJdbcTemplate(dataSource);
	}
	
	public List<UserComment> selectUserCommentByProductId (Integer productId,Integer offset, Integer size) {
		Map<String, Object> params = new HashMap<>();
		params.put("productId", productId);
		params.put("offset", offset);
		params.put("size", size);
		try {
    			return jdbc.query(UserCommentSqls.SELECT_BY_PRODUCT_ID_LIMIT, params, rowMapper);
		} catch(DataAccessException e) {
			e.printStackTrace();
			return null;
		}
    }
	
	public CommentStats selectStatsByProductId (Integer productId) {
		Map<String, Object> params = Collections.singletonMap("productId", productId);
		try {
			return jdbc.queryForObject(UserCommentSqls.SELECT_STATS_BY_PRODUCT_ID, params, userCommentStatsRowMapper);
		} catch(DataAccessException e) {
			e.printStackTrace();
			return null;
		}
	}
		
}
