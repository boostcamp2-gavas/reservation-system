package connect.reservation.dao;

public class ReservationCommentSqls {
	final static String GET_COMMENT_LIST = ""
			+ " SELECT comment_info.ruc_id, user_reservation.nickname, user_reservation.reservation_date, user_reservation.reservation_name, comment_info.score, comment_info.comment, comment_info.file_name, comment_info.save_file_name, comment_info.content_type, comment_info.img_count "
			+ " FROM "
			+ " ( "
			+ " SELECT u.id user_id, left(u.nickname, length(u.nickname)-4) nickname, replace(left(ri.reservation_date, 10), '-', '.') reservation_date, ri.reservation_name "
			+ " FROM users u, reservation_info ri "
			+ " WHERE ri.product_id = :product_id AND u.id = ri.user_id "
			+ " ) user_reservation, "
			+ " ( "
			+ " SELECT ruc.id ruc_id, ruc.user_id, ruc.score, ruc.comment, comment_image.file_name, comment_image.save_file_name, comment_image.content_type, count(ruc.id) img_count "
			+ " FROM reservation_user_comment ruc "
			+ " LEFT JOIN "
			+ " ( "
			+ " SELECT ruci.reservation_user_comment_id ruci_id, f.file_name, f.save_file_name, f.content_type "
			+ " FROM reservation_user_comment_image ruci, file f "
			+ " WHERE ruci.file_id = f.id AND f.content_type like '%한줄평이미지%' "
			+ " ) comment_image "
			+ " ON ruc.id = comment_image.ruci_id "
			+ " WHERE ruc.product_id = :product_id "
			+ " GROUP BY ruc.id "
			+ " ORDER BY ruc.id, comment_image.content_type"
			+ " ) comment_info "
			+ " WHERE user_reservation.user_id = comment_info.user_id "
			+ " ORDER BY user_reservation.reservation_date DESC";
	// 상세페이지 코멘트 이미지 크게보기 리스트 가져오기
	final static String GET_COMMENT_IMAGE_LIST = "SELECT f.file_name, f.save_file_name "
			+ "FROM reservation_user_comment_image ruci, file f "
			+ "WHERE ruci.reservation_user_comment_id = :reservation_user_comment_id AND ruci.file_id = f.id";
			
}

