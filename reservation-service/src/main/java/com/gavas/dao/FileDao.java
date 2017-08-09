package com.gavas.dao;

import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.gavas.dao.sqls.FileSqls.SELECT_FILE_ID_BY_PRODUCT_ID;

@Repository
public class FileDao {
    private NamedParameterJdbcTemplate jdbc;

    public FileDao(DataSource dataSource) {
        this.jdbc = new NamedParameterJdbcTemplate(dataSource);
    }

    public List<Long> selectFileIdsByProductId(Long productId) {
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("productId", productId);
        return jdbc.queryForList(SELECT_FILE_ID_BY_PRODUCT_ID, paramMap, Long.class);
    }
}
