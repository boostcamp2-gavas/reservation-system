package com.gavas.dao;

import com.gavas.domain.Review;
import com.gavas.domain.ReviewImage;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;

@Repository
public class ReviewDao {
    private NamedParameterJdbcTemplate jdbc;
    private SimpleJdbcInsert simpleJdbcInsert;
    private SimpleJdbcInsert simpleJdbcInsertImageTable;
    private RowMapper<Review> rowMapper = BeanPropertyRowMapper.newInstance(Review.class);

    public ReviewDao(DataSource dataSource) {
        this.jdbc = new NamedParameterJdbcTemplate(dataSource);
        this.simpleJdbcInsert = new SimpleJdbcInsert(dataSource)
                .withTableName("reservation_user_comment")
                .usingGeneratedKeyColumns("id");
        this.simpleJdbcInsertImageTable = new SimpleJdbcInsert(dataSource)
                .withTableName("reservation_user_comment_image")
                .usingGeneratedKeyColumns("id");

    }

    public Integer insert(Review review) {
        SqlParameterSource parameterSource = new BeanPropertySqlParameterSource(review);
        return simpleJdbcInsert.executeAndReturnKey(parameterSource).intValue();
    }

    public Integer insertWithFiles(Review review, List<Integer> fileIdList) {
        SqlParameterSource parameterSource = new BeanPropertySqlParameterSource(review);
        Integer reviewId = simpleJdbcInsert.executeAndReturnKey(parameterSource).intValue();
        for(Integer fileId : fileIdList) {
            ReviewImage reviewImage = new ReviewImage();
            reviewImage.setReservationUserCommentId(reviewId);
            reviewImage.setFileId(fileId);
            SqlParameterSource reviewImageParam = new BeanPropertySqlParameterSource(reviewImage);
            simpleJdbcInsertImageTable.execute(reviewImageParam);
        }
        return reviewId;
    }
}
