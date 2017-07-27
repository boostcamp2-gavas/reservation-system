package connect.reservation.dao;

public class ReservationInfoSqls {
	final static String SELECT_BY_SNS_ID = "SELECT * FROM users WHERE sns_id = :sns_id AND sns_type = 'Naver'";
	final static String UPDATE_BY_SNS_ID = "UPDATE users "
							+ "SET nickname = :nickname, sns_profile = :sns_profile, modify_date = :modify_date "
							+ "WHERE sns_id = :sns_id";
	final static String GET_USER_INFO = "SELECT username, tel, email"
			+ " FROM users "
			+ " WHERE id = :user_id;";
	
	final static String SELECT_BY_USER_ID = "SELECT r.id, r.reservation_type, r.general_ticket_count, r.youth_ticket_count, r.child_ticket_count, p.id  product_id, p.name product_name, d.display_start, d.display_end, d.place_name" + 
			" FROM reservation_info r" + 
			" INNER JOIN product p" + 
			" INNER JOIN display_info d" + 
			" ON r.product_id = p.id and p.id = d.product_id AND r.user_id = :user_id" +
			" ORDER BY reservation_type";
	
	final static String SELECT_COUNT = "SELECT reservation_type, COALESCE(COUNT(*), 0) cnt" + 
			" FROM reservation_info" + 
			" WHERE user_id = :user_id" + 
			" GROUP BY reservation_type";
}
