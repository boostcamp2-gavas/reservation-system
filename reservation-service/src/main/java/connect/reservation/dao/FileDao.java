package connect.reservation.dao;

import javax.sql.DataSource;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import connect.reservation.domain.File;
import connect.reservation.domain.ReservationUserCommentImage;



@Repository
public class FileDao {
	private NamedParameterJdbcTemplate jdbc; // sql 을 실행하기 위해 사용되는 객체
    private SimpleJdbcInsert insertAction; // insert 를 편리하게 하기 위한 객체
    private RowMapper<File> rowMapper = BeanPropertyRowMapper.newInstance(File.class); // 칼럼 이름을 보통 user_name 과 같이 '_'를 활용하는데 자바는 낙타표기법을 사용한다 이것을 자동 맵핑한다.
    private RowMapper<ReservationUserCommentImage> commentImageRowMapper = BeanPropertyRowMapper.newInstance(ReservationUserCommentImage.class);
    
    // Spring은 생성자를 통하여 주입을 하게 된다.
    public FileDao(DataSource dataSource) {
        this.jdbc = new NamedParameterJdbcTemplate(dataSource); // Datasource를 주입
        this.insertAction = new SimpleJdbcInsert(dataSource)  // Datasource를 주입
                .withTableName("file")   // table명을 지정
                .usingGeneratedKeyColumns("id"); // pk 칼럼을 지정
    }
    
    public int add(File file) {
    	SqlParameterSource params = new BeanPropertySqlParameterSource(file); 
        return insertAction.executeAndReturnKey(params).intValue();	
    }
    
    public int addCommentImage(ReservationUserCommentImage commentImage) {
    	SqlParameterSource params = new BeanPropertySqlParameterSource(commentImage);
    	return jdbc.update(FIleSqls.ADD_COMMENT_IMAGE, params);
    }
}
