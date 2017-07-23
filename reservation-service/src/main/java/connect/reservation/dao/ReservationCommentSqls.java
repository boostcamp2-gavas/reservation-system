package connect.reservation.dao;

public class ReservationCommentSqls {
	final static String GET_COMMENT_LIST = ""
			+ " SELECT ruc.id ruc_id, left(u.nickname, length(u.nickname)-4) nickname, ri.reservation_name, replace(left(ri.reservation_date, 10), '-', '.') reservation_date, ruc.score, ruc.comment, f.file_name, f.save_file_name, f.content_type, count(ruc.id) imgCount"
			+ " FROM reservation_info ri INNER JOIN users u ON ri.user_id = u.id"
			+ " INNER JOIN reservation_user_comment ruc ON u.id = ruc.user_id"
			+ " LEFT JOIN reservation_user_comment_image ruci ON ruc.id = ruci.reservation_user_comment_id"
			+ " LEFT JOIN file f ON ruci.file_id = f.id"
			+ " WHERE ri.product_id = :product_id AND ruc.product_id = :product_id"
			+ " GROUP BY ruc.id"
			+ " ORDER BY ri.reservation_date desc, ruci.id";
	// 상세페이지 코멘트 이미지 크게보기 리스트 가져오기
	final static String GET_COMMENT_IMAGE_LIST = "SELECT f.file_name, f.save_file_name "
			+ " FROM reservation_user_comment_image ruci, file f "
			+ " WHERE ruci.reservation_user_comment_id = :reservation_user_comment_id AND ruci.file_id = f.id";
			
}

