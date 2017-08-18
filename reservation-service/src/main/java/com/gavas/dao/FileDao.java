package com.gavas.dao;

import com.gavas.domain.FileDomain;
import com.gavas.exception.EmptyQueryResultException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
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

import static com.gavas.dao.sqls.FileSqls.SELECT_FILE_DOMAIN_BY_FILE_ID;
import static com.gavas.dao.sqls.FileSqls.SELECT_FILE_ID_BY_PRODUCT_ID;

@Repository
public class FileDao {
    private NamedParameterJdbcTemplate jdbc;
    private SimpleJdbcInsert insertAction;
    private RowMapper<FileDomain> rowMapper = BeanPropertyRowMapper.newInstance(FileDomain.class);

    @Autowired
    public FileDao(DataSource dataSource) {
        this.jdbc = new NamedParameterJdbcTemplate(dataSource);
        this.rowMapper = BeanPropertyRowMapper.newInstance(FileDomain.class);
        this.insertAction = new SimpleJdbcInsert(dataSource)
                .withTableName("file")
                .usingGeneratedKeyColumns("id");
    }

    public List<Long> selectFileIdsByProductId(Long productId) {
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("productId", productId);
        try {
            return jdbc.queryForList(SELECT_FILE_ID_BY_PRODUCT_ID, paramMap, Long.class);
        } catch (EmptyResultDataAccessException exception) {
            throw new EmptyQueryResultException("FileDomain Id");
        }
    }

    public FileDomain selectFileDomainByfileId(Long fileId) {
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("fileId", fileId);
        try {
            return jdbc.queryForObject(SELECT_FILE_DOMAIN_BY_FILE_ID, paramMap, rowMapper);
        } catch (EmptyResultDataAccessException exception) {
            throw new EmptyQueryResultException("FileDomain Id");
        }
    }

    public Integer addFile(FileDomain fileDomain) {
        SqlParameterSource param = new BeanPropertySqlParameterSource(fileDomain);
        return insertAction.executeAndReturnKey(param).intValue();
    }
}
