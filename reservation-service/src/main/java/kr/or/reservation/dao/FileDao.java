package kr.or.reservation.dao;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSourceUtils;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import kr.or.reservation.domain.FileDomain;
import kr.or.reservation.dto.ImgDTO;
import kr.or.reservation.serviceImpl.FileSqls;
import kr.or.reservation.sqls.ImgSqls;

@Repository
public class FileDao {

	private NamedParameterJdbcTemplate jdbc;
	private SimpleJdbcInsert insertAction;
	private RowMapper<FileDomain> rowMapper = BeanPropertyRowMapper.newInstance(FileDomain.class);

	public FileDao(DataSource dataSource) {
		this.jdbc = new NamedParameterJdbcTemplate(dataSource);
		this.insertAction = new SimpleJdbcInsert(dataSource) 
				.withTableName("file") 
				.usingGeneratedKeyColumns("id"); 
	}


	// 여러개의 데이터에 대해, insert 마다 connection을 열여 접근하는건 비효율적.
	// 때문에 batch를 이용하여 한번에 보내도록 구현. 
	public boolean insertArray(FileDomain[] files) {
		SqlParameterSource[] batch = SqlParameterSourceUtils.createBatch(files);
		return insertAction.executeBatch(batch) != null;
	}
}
