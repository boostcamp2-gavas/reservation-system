package connect.reservation.dao;

import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import connect.reservation.domain.Users;

@Repository
public class UsersDao {
	private NamedParameterJdbcTemplate jdbc; // sql 을 실행하기 위해 사용되는 객체
    private SimpleJdbcInsert insertAction; // insert 를 편리하게 하기 위한 객체
    private RowMapper<Users> rowMapper = BeanPropertyRowMapper.newInstance(Users.class); // 칼럼 이름을 보통 user_name 과 같이 '_'를 활용하는데 자바는 낙타표기법을 사용한다 이것을 자동 맵핑한다.
    
    // Spring은 생성자를 통하여 주입을 하게 된다.
    public UsersDao(DataSource dataSource) {
        this.jdbc = new NamedParameterJdbcTemplate(dataSource); // Datasource를 주입
        this.insertAction = new SimpleJdbcInsert(dataSource)  // Datasource를 주입
                .withTableName("users")   // table명을 지정
                .usingGeneratedKeyColumns("id"); // pk 칼럼을 지정
    }
    
    public int insert(Users user){
        SqlParameterSource params = new BeanPropertySqlParameterSource(user); 
        return insertAction.executeAndReturnKey(params).intValue();	
    }
    
    public Users selectBySnsId(String sns_id){
        Map<String, Object> params = new HashMap<>();
        params.put("sns_id", sns_id);
        try {
        	return jdbc.queryForObject(UsersSqls.SELECT_BY_SNS_ID,params,rowMapper);	
        } catch (EmptyResultDataAccessException e) {
        	return null;
        }
    }
    
    public int updateSnsUser(String sns_id, String nickname, String sns_profile, String modify_date) {
//    	SqlParameterSource params = new BeanPropertySqlParameterSource(user);
    	Map<String, Object> params = new HashMap<>();
        params.put("sns_id", sns_id);
        params.put("nickname", nickname);
        params.put("sns_profile", sns_profile);
        params.put("modify_date", modify_date);
    	
    	return jdbc.update(UsersSqls.UPDATE_BY_SNS_ID, params);
    }
    
    public Users getUserInfo(int user_id) {
    	Map<String, Object> params = new HashMap<>();
        params.put("user_id", user_id);
        try {
        	return jdbc.queryForObject(UsersSqls.GET_USER_INFO,params,rowMapper);
        } catch(EmptyResultDataAccessException e) {
        	return null;
        }
    }
}
