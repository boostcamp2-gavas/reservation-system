package kr.or.reservation.dao;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import kr.or.reservation.dto.ImgDTO;
import kr.or.reservation.sqls.ImgSqls;

@Repository
public class ImgDao {

	private NamedParameterJdbcTemplate jdbc;
	private RowMapper<ImgDTO> rowMapper = BeanPropertyRowMapper.newInstance(ImgDTO.class);

	public ImgDao(DataSource dataSource) {
		this.jdbc = new NamedParameterJdbcTemplate(dataSource);
	}

	public List<ImgDTO> selectList(int id) {
		Map<String, ?> params = Collections.singletonMap("id", id);
		return jdbc.query(ImgSqls.SELECTBYPRODUCT_ID, params, rowMapper);
	}

	public ImgDTO selectOne(long id) {
		Map<String, ?> params = Collections.singletonMap("id", id);
		return jdbc.queryForObject(ImgSqls.SELECTBYFILE_ID, params, rowMapper);
	}

}
