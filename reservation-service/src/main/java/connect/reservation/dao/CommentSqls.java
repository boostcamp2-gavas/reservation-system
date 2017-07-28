package connect.reservation.dao;

public class CommentSqls {
	final static String GET_COMMENT_LIST = "SELECT ruc.id ruc_id, u.nickname, ri.reservation_name, ri.reservation_date, ruc.score, ruc.comment, f.id file_id, f.file_name, f.save_file_name, f.content_type, count(ruc.id) imgCount"
			+ " FROM reservation_info ri INNER JOIN users u ON ri.user_id = u.id"
			+ " INNER JOIN reservation_user_comment ruc ON u.id = ruc.user_id"
			+ " LEFT JOIN reservation_user_comment_image ruci ON ruc.id = ruci.reservation_user_comment_id"
			+ " LEFT JOIN file f ON ruci.file_id = f.id"
			+ " WHERE ri.product_id = 1 AND ruc.product_id = 1"
			+ " GROUP BY ruc.id"
			+ " ORDER BY ri.reservation_date desc";
			
	// 상세페이지 코멘트 이미지 크게보기 리스트 가져오기
	final static String GET_COMMENT_IMAGE_LIST = "SELECT f.id file_id, f.file_name, f.save_file_name "
			+ " FROM reservation_user_comment_image ruci, file f "
			+ " WHERE ruci.reservation_user_comment_id = :reservation_user_comment_id AND ruci.file_id = f.id";
			
	final static String GET_RESERVAION_NAME = "SELECT p.id product_id, p.name reservation_name"
			+ " FROM reservation_info ri, product p "
			+ " WHERE ri.id = :reservation_id AND ri.product_id = p.id;";
	
	final static String ADD = "INSERT INTO reservation_user_comment(product_id, user_id, score, comment, create_date) "
			+ " VALUES(:product_id, :user_id, :score, :comment, sysdate())";
}