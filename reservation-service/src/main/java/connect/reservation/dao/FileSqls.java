package connect.reservation.dao;

public class FileSqls {
	static final String SELECT_BY_ID = "SELECT * from file where id = :id";
	static final String ADD_COMMENT_IMAGE = "INSERT INTO reservation_user_comment_image(reservation_user_comment_id, file_id)"
			+ " VALUES(:reservation_user_comment_id, :file_id)";
}
