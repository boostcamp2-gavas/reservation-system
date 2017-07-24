package kr.or.reservation.sqls;

public class UserSqls {
	public static final String SELECTBYID = "select count(*) from users where sns_id = :id";
	public static final String SELECT_ID_BY_SNSID = "select id from users where sns_id = :id";

}
