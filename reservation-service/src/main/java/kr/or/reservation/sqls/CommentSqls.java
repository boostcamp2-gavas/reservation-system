package kr.or.reservation.sqls;

public class CommentSqls {

	public final static String SELECT_ALL = "select users.nickname,comment.*,file.id as file_id ,count(*) as count from reservation_user_comment as comment inner join users " + 
			"	on comment.user_id = users.id  " + 
			"    left outer join reservation_user_comment_image as img " + 
			"on  comment.id = img.reservation_user_comment_id " + 
			"left outer join file on img.file_id = file.id " + 
			"where product_id = :id " + 
			"group by reservation_user_comment_id;";
	
	public final static String SELECT_COUNT_AND_AVGSCORE = "SELECT  count(*) as amount_of_count,ROUND(AVG(score),1) as avg_score " + 
			"FROM reservation_user_comment " + 
			" where product_id = :id;";

	public final static String SELECT_FILEID_BY_COMMENTID = "SELECT file_id FROM reservation_user_comment_image where reservation_user_comment_id =:id";
}
