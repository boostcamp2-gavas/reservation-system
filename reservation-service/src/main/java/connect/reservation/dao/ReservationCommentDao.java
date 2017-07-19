package connect.reservation.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import connect.reservation.dto.ReservationComment;



@Repository
public class ReservationCommentDao {
	private NamedParameterJdbcTemplate jdbc; // sql 을 실행하기 위해 사용되는 객체
    private SimpleJdbcInsert insertAction; // insert 를 편리하게 하기 위한 객체
    private RowMapper<ReservationComment> rowMapper = BeanPropertyRowMapper.newInstance(ReservationComment.class); // 칼럼 이름을 보통 user_name 과 같이 '_'를 활용하는데 자바는 낙타표기법을 사용한다 이것을 자동 맵핑한다.
    
    // Spring은 생성자를 통하여 주입을 하게 된다.
    public ReservationCommentDao(DataSource dataSource) {
        this.jdbc = new NamedParameterJdbcTemplate(dataSource); // Datasource를 주입
//        this.insertAction = new SimpleJdbcInsert(dataSource)  // Datasource를 주입
//                .withTableName("category")   // table명을 지정
//                .usingGeneratedKeyColumns("id"); // pk 칼럼을 지정
    }
    
    public List<ReservationComment> getCommentList(int product_id) {
    	Map<String, Object> params = new HashMap<>();
    	params.put("product_id", product_id);
    	return jdbc.query(ReservationCommentSqls.GET_COMMENT_LIST, params, rowMapper);
    }
    
    public List<ReservationComment> getImageList(int reservation_user_comment_id) {
    	Map<String, Object> params = new HashMap<>();
    	params.put("reservation_user_comment_id", reservation_user_comment_id);
    	return jdbc.query(ReservationCommentSqls.GET_COMMENT_IMAGE_LIST, params, rowMapper);
    }
}
