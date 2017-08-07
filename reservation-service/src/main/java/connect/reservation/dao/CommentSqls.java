package connect.reservation.dao;

public class CommentSqls {
	final static String GET_COMMENT_LIST = "SELECT distinct ruc.id ruc_id, u.nickname, p.name product_name, ri.reservation_date, ruc.score, ruc.comment, f.id file_id, count(ruc.id) imgCount"
			+ " FROM reservation_info ri INNER JOIN users u ON ri.user_id = u.id AND ri.reservation_type = 'USED'"
			+ " INNER JOIN reservation_user_comment ruc ON u.id = ruc.user_id"
			+ " INNER JOIN product p ON ri.product_id = p.id"
			+ " LEFT JOIN reservation_user_comment_image ruci ON ruc.id = ruci.reservation_user_comment_id"
			+ " LEFT JOIN file f ON ruci.file_id = f.id"
			+ " WHERE ri.product_id = :product_id AND ri.product_id = ruc.product_id"
			+ " GROUP BY  ruc.id"
			+ " ORDER BY ri.reservation_date desc"
			+ " limit :start, :end";
	final static String GET_SCORE_LIST = "SELECT score FROM reservation_user_comment WHERE product_id = :product_id";
			
	// 상세페이지 코멘트 이미지 크게보기 리스트 가져오기
	final static String GET_COMMENT_IMAGE_LIST = "SELECT f.id file_id, f.file_name, f.save_file_name "
			+ " FROM reservation_user_comment_image ruci, file f "
			+ " WHERE ruci.reservation_user_comment_id = :reservation_user_comment_id AND ruci.file_id = f.id";
			
	final static String GET_RESERVAION_NAME = "SELECT p.id , p.name reservation_name"
			+ " FROM reservation_info ri, product p "
			+ " WHERE ri.id = :reservation_id AND ri.product_id = p.id;";
	
	final static String ADD = "INSERT INTO reservation_user_comment(product_id, user_id, score, comment, create_date) "
			+ " VALUES(:product_id, :user_id, :score, :comment, sysdate())";
}