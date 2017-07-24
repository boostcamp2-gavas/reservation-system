package connect.reservation.dao;

public class UsersSqls {
	final static String SELECT_BY_SNS_ID = "SELECT * FROM users WHERE sns_id = :sns_id AND sns_type = 'Naver'";
	final static String UPDATE_BY_SNS_ID = "UPDATE users "
							+ "SET nickname = :nickname, sns_profile = :sns_profile, modify_date = :modify_date "
							+ "WHERE sns_id = :sns_id";
	final static String GET_USER_INFO = "SELECT username, tel, email"
			+ " FROM users "
			+ " WHERE id = :user_id;";
}
