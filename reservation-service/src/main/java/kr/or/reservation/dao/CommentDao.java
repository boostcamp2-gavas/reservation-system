package kr.or.reservation.dao;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.apache.log4j.Logger;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import kr.or.reservation.domain.AVGForComment;
import kr.or.reservation.dto.CommentDTO;
import kr.or.reservation.sqls.CommentSqls;

@Repository
public class CommentDao {
	Logger log = Logger.getLogger(this.getClass());
	
	private NamedParameterJdbcTemplate jdbc;
	private RowMapper<CommentDTO> rowMapper = BeanPropertyRowMapper.newInstance(CommentDTO.class);
	private RowMapper<AVGForComment> avgRowMapper = BeanPropertyRowMapper.newInstance(AVGForComment.class);
	
	
	public CommentDao(DataSource dataSource) {
		this.jdbc = new NamedParameterJdbcTemplate(dataSource); 
	}
	
	public List<CommentDTO> selectByProductId(int productId) {
		Map<String , ?> map = Collections.singletonMap("id",productId);
		return jdbc.query(CommentSqls.SELECT_ALL,map,rowMapper);
	}
	
	// 이름이 너무긴데? 
	public List<Map<String,Object>> selectAvgScoreByProductId(int productId) {
		Map<String , ?> map = Collections.singletonMap("id",productId);
		List<Map<String,Object>> list = jdbc.queryForList(CommentSqls.SELECT_COUNT_AND_AVGSCORE,map);
		return list;
	}
	
	public List<?> getFileId(int commnetId){
		Map<String , ?> map = Collections.singletonMap("id",commnetId);
		return jdbc.queryForList(CommentSqls.SELECT_FILEID_BY_COMMENTID,map);
	}
	
}
