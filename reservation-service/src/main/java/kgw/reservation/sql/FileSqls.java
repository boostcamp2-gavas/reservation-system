package kgw.reservation.sql;

public class FileSqls {
	public final static String SELECT_JOIN_PRODUCT_IMAGE_BY_PRODUCT_ID = "select "
			+ "															f.id, f.file_name, "
			+ "															f.save_file_name, "
			+ "															f.file_length, "
			+ "															f.content_type, "
			+ "															f.delete_flag, "
			+ "															f.create_date, "
			+ "															f.modify_date, "
			+ "															p_i.type"
			+"													  		from file f"
			+"												  			inner join product_image p_i on f.id = p_i.file_id"
			+"													  		where p_i.product_id = :productId"
			+"		    												  	order by f.id asc";
	
	public final static String SELECT_JOIN_COMMENT_IMAGE_BY_PRODUCT_ID = "select "
			+ "															r_u_c.id as reservationUserCommentId, "
			+ "															f.save_file_name, "
			+ "															f.id,"
			+ "														 	f.user_id as userId"
			+ "														  	from reservation_user_comment r_u_c"
			+ "													  		inner join reservation_user_comment_image r_u_c_i on r_u_c.id = r_u_c_i.reservation_user_comment_id"
			+ "													  		inner join file f on r_u_c_i.file_id = f.id"
			+ "													  		where r_u_c.product_id = :productId and r_u_c.user_id in (:userIds)"
			+ "													  		order by f.id asc";
	public final static String SELECT_BY_ID = "select "
			+ "								  f.file_name, "
			+ "								  f.save_file_name, "
			+ "								  f.file_length, "
			+ "								  f.content_type"
			+ "								  from file f"
			+ "								  where f.id = :id";
}
