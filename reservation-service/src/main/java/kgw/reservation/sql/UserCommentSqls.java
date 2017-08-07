package kgw.reservation.sql;

public class UserCommentSqls {
	public final static String SELECT_BY_PRODUCT_ID_LIMIT = "select "
			+ "												r_u_c.id, "
			+ "												r_u_c.score, "
			+ "												r_u_c.comment,"
			+ "												p.name, "
			+ "												r_u_c.modify_date, "
			+ "												r_u_c.create_date, "
			+ "												r_u_c.user_id, "
			+ "												u.username, "
			+ "												r_i.reservation_date"
			+"												from reservation_user_comment r_u_c"
			+ "												inner join users u on r_u_c.user_id = u.id"
			+ "												inner join product p on r_u_c.product_id = p.id"
			+ "												inner join reservation_info r_i on r_u_c.user_id  =  r_i.user_id and r_u_c.product_id = r_i.product_id"
			+"												where r_u_c.product_id =:productId"
			+ "												order by r_u_c.id desc"
			+"												limit :offset, :size";
	
	public final static String SELECT_STATS_BY_PRODUCT_ID = "select "
			+ "												count(*) as count, "
			+ "												avg(score) as averageScore"
			+ "												from reservation_user_comment"
			+ "												where product_id =:productId";
		
	
}
