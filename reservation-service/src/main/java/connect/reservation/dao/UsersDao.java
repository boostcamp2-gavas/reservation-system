package connect.reservation.dao;

<<<<<<< HEAD
=======
import java.sql.Timestamp;
>>>>>>> 675e75dfc3b5ee0e722079d046479cafa81aa8d7
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

<<<<<<< HEAD
import connect.reservation.domain.Users;
=======
import connect.reservation.domain.User;
>>>>>>> 675e75dfc3b5ee0e722079d046479cafa81aa8d7

@Repository
public class UsersDao {
	private NamedParameterJdbcTemplate jdbc; // sql 을 실행하기 위해 사용되는 객체
    private SimpleJdbcInsert insertAction; // insert 를 편리하게 하기 위한 객체
<<<<<<< HEAD
    private RowMapper<Users> rowMapper = BeanPropertyRowMapper.newInstance(Users.class); // 칼럼 이름을 보통 user_name 과 같이 '_'를 활용하는데 자바는 낙타표기법을 사용한다 이것을 자동 맵핑한다.
=======
    private RowMapper<User> rowMapper = BeanPropertyRowMapper.newInstance(User.class); // 칼럼 이름을 보통 user_name 과 같이 '_'를 활용하는데 자바는 낙타표기법을 사용한다 이것을 자동 맵핑한다.
>>>>>>> 675e75dfc3b5ee0e722079d046479cafa81aa8d7
    
    // Spring은 생성자를 통하여 주입을 하게 된다.
    public UsersDao(DataSource dataSource) {
        this.jdbc = new NamedParameterJdbcTemplate(dataSource); // Datasource를 주입
        this.insertAction = new SimpleJdbcInsert(dataSource)  // Datasource를 주입
                .withTableName("users")   // table명을 지정
                .usingGeneratedKeyColumns("id"); // pk 칼럼을 지정
    }
    
<<<<<<< HEAD
    public int insert(Users user){
=======
    public int insert(User user){
>>>>>>> 675e75dfc3b5ee0e722079d046479cafa81aa8d7
        SqlParameterSource params = new BeanPropertySqlParameterSource(user); 
        return insertAction.executeAndReturnKey(params).intValue();	
    }
    
<<<<<<< HEAD
    public Users selectBySnsId(String sns_id){
=======
    public User selectBySnsId(String sns_id){
>>>>>>> 675e75dfc3b5ee0e722079d046479cafa81aa8d7
        Map<String, Object> params = new HashMap<>();
        params.put("sns_id", sns_id);
        try {
        	return jdbc.queryForObject(UsersSqls.SELECT_BY_SNS_ID,params,rowMapper);	
        } catch (EmptyResultDataAccessException e) {
        	return null;
        }
    }
    
<<<<<<< HEAD
    public int updateSnsUser(String sns_id, String nickname, String sns_profile, String modify_date) {
=======
    public int updateSnsUser(String sns_id, String nickname, String sns_profile, Timestamp modify_date) {
>>>>>>> 675e75dfc3b5ee0e722079d046479cafa81aa8d7
//    	SqlParameterSource params = new BeanPropertySqlParameterSource(user);
    	Map<String, Object> params = new HashMap<>();
        params.put("sns_id", sns_id);
        params.put("nickname", nickname);
        params.put("sns_profile", sns_profile);
        params.put("modify_date", modify_date);
    	
    	return jdbc.update(UsersSqls.UPDATE_BY_SNS_ID, params);
    }
    
<<<<<<< HEAD
    public Users getUserInfo(int user_id) {
=======
    public User getUserInfo(int user_id) {
>>>>>>> 675e75dfc3b5ee0e722079d046479cafa81aa8d7
    	Map<String, Object> params = new HashMap<>();
        params.put("user_id", user_id);
        try {
        	return jdbc.queryForObject(UsersSqls.GET_USER_INFO,params,rowMapper);
        } catch(EmptyResultDataAccessException e) {
        	return null;
        }
    }
}
