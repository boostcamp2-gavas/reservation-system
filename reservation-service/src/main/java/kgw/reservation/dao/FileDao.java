package kgw.reservation.dao;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import kgw.reservation.domain.FileDomain;
import kgw.reservation.dto.FileImage;
import kgw.reservation.sql.FileSqls;

@Repository
public class FileDao {
	private NamedParameterJdbcTemplate jdbc;
	private SimpleJdbcInsert insertAction;
    private RowMapper<FileImage> fileImageRowMapper = BeanPropertyRowMapper.newInstance(FileImage.class);
    private RowMapper<FileDomain> fileRowMapper = BeanPropertyRowMapper.newInstance(FileDomain.class);
    @Autowired
    public FileDao(DataSource dataSource) {
    		this.jdbc = new NamedParameterJdbcTemplate(dataSource);
    		this.insertAction = new SimpleJdbcInsert(dataSource)
    				.withTableName("file")
    				.usingGeneratedKeyColumns("id");

    }
    
    public List<FileImage> selectJoinProductImageByProductId(Integer productId){
		Map<String, Object> params = Collections.singletonMap("productId", productId);
		
		try {
    			return jdbc.query(FileSqls.SELECT_JOIN_PRODUCT_IMAGE_BY_PRODUCT_ID, params, fileImageRowMapper);
		} catch(DataAccessException e) {
			return null;
		}
    }
    
    public List<FileImage> selectJoinCommentImageByProductIdAndUserId (Integer productId, Set<Integer> userIds) {
		Map<String, Object> params = new HashMap<>();
		params.put("productId", productId);
		params.put("userIds", userIds);
		try {
			return jdbc.query(FileSqls.SELECT_JOIN_COMMENT_IMAGE_BY_PRODUCT_ID, params, fileImageRowMapper);
		} catch(DataAccessException e) {
			e.printStackTrace();
			return null;
		}
	}
    public FileDomain selectById(Integer id) {
    		Map<String, Object> params = Collections.singletonMap("id", id);
    		try {
    			return jdbc.queryForObject(FileSqls.SELECT_BY_ID, params, fileRowMapper);
		} catch(DataAccessException e) {
			return null;
		}
    }

	public FileDomain insert(FileDomain file) {
		SqlParameterSource params = new BeanPropertySqlParameterSource(file);
		Integer fileId = insertAction.executeAndReturnKey(params).intValue();
		file.setId(fileId);
		return file;
	}
	
	public Integer delete(Integer id) {
		Map<String, Object> params = Collections.singletonMap("id", id);
		return jdbc.update(FileSqls.DELETE_BY_ID, params);
	}
	
	public Integer update(List<Integer> ids) {
		Map<String, Object> params =  Collections.singletonMap("ids", ids);
		return jdbc.update(FileSqls.UPDATE_BY_IDS, params);
		
	}
}
