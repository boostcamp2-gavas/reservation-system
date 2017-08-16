package com.gavas.dao;

import com.gavas.domain.FileDomain;
import com.gavas.exception.EmptyQueryResultException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
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
    private RowMapper<FileDomain> rowMapper = BeanPropertyRowMapper.newInstance(FileDomain.class);

    public FileDao(DataSource dataSource) {
        this.jdbc = new NamedParameterJdbcTemplate(dataSource);
    }

    public List<Long> selectFileIdsByProductId(Long productId) {
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("productId", productId);
        try {
            return jdbc.queryForList(SELECT_FILE_ID_BY_PRODUCT_ID, paramMap, Long.class);
        } catch (EmptyResultDataAccessException exception) {
            throw new EmptyQueryResultException("File Id");
        }
    }

    public FileDomain selectFileDomainByfileId(Long fileId) {
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("fileId", fileId);
        try {
            return jdbc.queryForObject(SELECT_FILE_DOMAIN_BY_FILE_ID, paramMap, rowMapper);
        } catch (EmptyResultDataAccessException exception) {
            throw new EmptyQueryResultException("File Id");
        }
    }
}
