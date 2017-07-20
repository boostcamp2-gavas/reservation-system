package connect.reservation.dao;

public class UsersSqls {
	final static String SELECT_BY_SNS_ID = "SELECT * FROM users WHERE sns_id = :sns_id AND sns_type = 'Naver'";

}
