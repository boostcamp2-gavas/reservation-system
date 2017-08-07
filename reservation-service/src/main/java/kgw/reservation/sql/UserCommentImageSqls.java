package kgw.reservation.sql;

public class UserCommentImageSqls {
	public final static String INSERT_BY_COMMENT_AND_FILE_ID_BATCH = "INSERT"
			+ "														INTO reservation_user_comment_image"
			+ "														("
			+ "														reservation_user_comment_id, "
			+ "														file_id"
			+ "														)"
			+ "														values"
			+ "														("
			+ "														:reservationUserCommentId,"
			+ "														:fileId"
			+ "														)";	 
}
