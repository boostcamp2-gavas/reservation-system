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
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import kr.or.reservation.domain.Comment;
import kr.or.reservation.sqls.CommentSqls;

@Repository
public class CommentDao {
	Logger log = Logger.getLogger(this.getClass());
	
	private NamedParameterJdbcTemplate jdbc;
	private SimpleJdbcInsert insertAction;
	private RowMapper<Comment> rowMapper = BeanPropertyRowMapper.newInstance(Comment.class);
	
	
	public CommentDao(DataSource dataSource) {
		this.jdbc = new NamedParameterJdbcTemplate(dataSource); 
		this.insertAction = new SimpleJdbcInsert(dataSource) 
				.withTableName("reservation_user_comment") 
				.usingGeneratedKeyColumns("id"); 
	}	
	
	public Long insert(Comment comment) {
		SqlParameterSource params = new BeanPropertySqlParameterSource(comment);
		return insertAction.executeAndReturnKey(params).longValue();
	}

	
	public List<Comment> select(int productId) {
		Map<String , ?> map = Collections.singletonMap("id",productId);
		return jdbc.query(CommentSqls.SELCET_ALL,map,rowMapper);
	}
	
	public List<Comment> selectByProductId(int productId, int start, int amount) {
		Map<String , Integer> map = new HashMap<>();
		map.put("productId", productId );
		map.put("start", start );
		map.put("amount", amount );
		
		return jdbc.query(CommentSqls.SELECT_BY_PRODUCT_ID_S_A, map, rowMapper);
	}
	
	public List<Map<String,Object>> selectAvgScoreByProductId(int productId) {
		Map<String , ?> map = Collections.singletonMap("id",productId);
		List<Map<String,Object>> list = jdbc.queryForList(CommentSqls.SELECT_COUNT_AND_AVGSCORE,map);
		return list;
	}
	
	public List<?> selectFileId(int commnetId){
		Map<String , ?> map = Collections.singletonMap("id",commnetId);
		return jdbc.queryForList(CommentSqls.SELECT_FILEID_BY_COMMENTID,map);
	}
	
	public int updateFileName(int commnetId,int fileId){
		Map<String , Integer> map = new HashMap<>();
		map.put("id", commnetId);
		map.put("imageName", fileId);
		return jdbc.update(CommentSqls.UPDATE_FIRST_FILE,map);
	}
	
	
}
