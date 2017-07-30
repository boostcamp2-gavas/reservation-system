package kr.or.reservation.dao;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSourceUtils;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import kr.or.reservation.domain.CommentImage;
import kr.or.reservation.domain.FileDomain;
import kr.or.reservation.dto.ImgDTO;
import kr.or.reservation.sqls.ImgSqls;

@Repository
public class CommentImageDao {

	private NamedParameterJdbcTemplate jdbc;
	private SimpleJdbcInsert insertAction;
	//private RowMapper<CommentImage> rowMapper = BeanPropertyRowMapper.newInstance(CommentImage.class);

	public CommentImageDao(DataSource dataSource) {
		this.jdbc = new NamedParameterJdbcTemplate(dataSource);
		this.insertAction = new SimpleJdbcInsert(dataSource) 
				.withTableName("reservation_user_comment_image") 
				.usingGeneratedKeyColumns("id"); 
	}

	public int[] insertArray(CommentImage[] commentImage) {
		SqlParameterSource[] batch = SqlParameterSourceUtils.createBatch(commentImage);
		return insertAction.executeBatch(batch);
	}


	

}
