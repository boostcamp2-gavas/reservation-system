package kr.or.reservation.sqls;

public class CommentSqls {

/*	public final static String SELECT_ALL = "select users.nickname,comment.*,file.id as file_id ,count(*) as count from reservation_user_comment as comment inner join users " + 
			"	on comment.user_id = users.id  " + 
			"    left outer join reservation_user_comment_image as img " + 
			"on  comment.id = img.reservation_user_comment_id " + 
			"left outer join file on img.file_id = file.id " + 
			"where product_id = :id " + 
			"group by reservation_user_comment_id;";*/
	
	public final static String SELCET_ALL = "select users.nickname,comment.* " + 
			"from reservation_user_comment as comment inner join users " + 
			"on comment.user_id = users.id " + 
			"where product_id = :id; ";
	
	public final static String SELECT_COUNT_AND_AVGSCORE = "SELECT  count(*) as amount_of_count,ROUND(AVG(score),1) as avg_score " + 
			"FROM reservation_user_comment " + 
			" where product_id = :id;";

	public final static String SELECT_FILEID_BY_COMMENTID = "SELECT file_id FROM reservation_user_comment_image where reservation_user_comment_id =:id";

	public final static String UPDATE_FIRST_FILE = "UPDATE reservation_user_comment " + 
			"SET " + 
			"first_image_save_file_name = :imageName," +
			"image_count = :length " + 
			"WHERE id = :id ";
	
	public final static String SELECT_BY_PRODUCT_ID_S_A = "select t2.nickname, t1.* from reservation_user_comment as t1"
			+ " inner join users as t2 on t1.user_id = t2.id"
			+ " where t1.product_id = :productId limit :start ,:amount";

}
