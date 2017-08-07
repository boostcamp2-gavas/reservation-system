package kgw.reservation.sql;

public class UserSqls {
	public final static String SELECT_BY_SNS_ID = "select"
			+ "									  id,"
			+ "									  username, "
			+ "									  email, "
			+ "									  tel,"
			+ "									  nickname,"
			+ "									  sns_id,"
			+ "									  sns_type,"
			+ "									  sns_profile,"
			+ "									  admin_flag"
			+ "									  from users"
			+ "									  where sns_id = :snsId"; 
}
