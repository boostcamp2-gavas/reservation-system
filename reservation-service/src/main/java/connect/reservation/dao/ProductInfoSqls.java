package connect.reservation.dao;

public class ProductInfoSqls {
	final static String endNum = "10";
	
	final static String COUNT_PRODUCT = "SELECT count(*) FROM product";
	final static String COUNT_CATEGORY_PRODUCT = "SELECT count(*) FROM product WHERE category_id = :category_id";
	// 메인페이지 상품 리스트
	final static String GET_MAIN_INFO = "SELECT s1.id product_id, s1.category_id, s1.name product_name, s1.description , s1.place_name, s2.file_name, s2.save_file_name "
			+ "FROM "
			+ "(select p.id, p.category_id, p.name, p.description, di.place_name  "
			+ "from product p, display_info di "
			+ "where p.id = di.product_id) s1 "
			+ "INNER JOIN "
			+ "(select p.id, f.file_name, f.save_file_name, pi.type "
			+ "from product p, product_image pi, file f "
			+ "where p.id = pi.product_id and pi.file_id = f.id) s2 "
			+ "ON s1.id = s2.id "
			+ "WHERE s2.type = 1 "
			+ "ORDER BY s1.id "
			+ "LIMIT :start , "+endNum;
	// 메인페이지 카테고리별 상품 리스트
	final static String GET_CATEGORY_INFO = "SELECT s1.id product_id, s1.category_id, s1.name product_name, s1.description , s1.place_name, s2.file_name, s2.save_file_name "
			+ "FROM "
			+ "(select p.id, p.category_id, p.name, p.description, di.place_name  "
			+ "from product p, display_info di "
			+ "where p.id = di.product_id) s1 "
			+ "INNER JOIN "
			+ "(select p.id, f.file_name, f.save_file_name, pi.type "
			+ "from product p, product_image pi, file f "
			+ "where p.id = pi.product_id and pi.file_id = f.id) s2 "
			+ "ON s1.id = s2.id "
			+ "WHERE s2.type = 1 AND s1.category_id = :categoryId "
			+ "ORDER BY s1.id "
			+ "LIMIT :start , "+endNum;
	// 상세페이지 상품 이미지
	final static String GET_PRODUCT_IMAGE = "select p.name product_name, p.description, f.file_name, f.save_file_name "
			+ "from product p, product_image pi, file f "
			+ "where p.id = :product_id AND p.id = pi.product_id AND pi.file_id = f.id AND f.content_type like '%배너이미지%' "
			+ "order by pi.type"; // 대표이미지 먼저 가져오도록
	// 상세페이지 상품 정보
	final static String GET_PRODUCT_DETAIL = "SELECT p.name product_name, pd.content, di.observation_time, replace(left(di.display_start, 10), '-', '.') display_start, replace(left(di.display_end, 10), '-', '.') display_end, p.event, sales_end, sales_flag, di.place_name, di.place_lot, di.place_street, di.tel, di.homepage, di.email "
			+ "FROM product p, product_detail pd, display_info di "
			+ "WHERE p.id = :product_id AND p.id = pd.product_id AND p.id = di.product_id";
	// 상세페이지 상품 공지사항 이미지
	final static String GET_PRODUCT_NOTICE_IMAGE = "SELECT f.file_name, f.save_file_name "
			+ "FROM product_image pi, file f "
			+ "WHERE pi.product_id = :product_id AND pi.file_id = f.id AND f.content_type like '%공지사항%' "
			+ "ORDER BY f.content_type";
	// 상세페이지 상품 공연정보 이미지
	final static String GET_PRODUCT_INFO_IMAGE = "SELECT f.file_name, f.save_file_name "
			+ "FROM product_image pi, file f "
			+ "WHERE pi.product_id = :product_id AND pi.file_id = f.id AND f.content_type like '%공연정보%' "
			+ "ORDER BY f.content_type";
}

