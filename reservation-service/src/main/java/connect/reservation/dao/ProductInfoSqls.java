package connect.reservation.dao;

public class ProductInfoSqls {
	final static String endNum = "10";
	
	final static String COUNT_PRODUCT = "SELECT count(*) FROM product";
	final static String COUNT_CATEGORY_PRODUCT = "SELECT count(*) FROM product WHERE category_id = :category_id";
	final static String GET_MAIN_INFO = "SELECT p.id product_id, p.category_id category_id, p.name product_name, p.description description, di.place_name, f.file_name, f.save_file_name"
			+ " FROM product p"
			+ " INNER JOIN display_info di ON p.id = di.product_id"
			+ " LEFT JOIN product_image pi ON p.id = pi.product_id"
			+ " LEFT JOIN file f ON pi.file_id = f.id"
			+ " WHERE pi.type = 1"
			+ " ORDER BY p.id"
			+ " LIMIT :start , "+endNum;
	final static String GET_CATEGORY_INFO = "SELECT p.id product_id, p.category_id category_id, p.name product_name, p.description description, di.place_name, f.file_name, f.save_file_name"
			+ "	FROM product p"
			+ " INNER JOIN display_info di ON p.id = di.product_id"
			+ "	LEFT JOIN product_image pi ON p.id = pi.product_id"
			+ " LEFT JOIN file f ON pi.file_id = f.id"
			+ " WHERE pi.type = 1 AND p.category_id = :category_id"
			+ " ORDER BY p.id"
			+ " LIMIT :start , "+endNum;

	final static String GET_PRODUCT_IMAGE = "select p.name product_name, p.description, f.file_name, f.save_file_name "
			+ "from product p, product_image pi, file f "
			+ "where p.id = :product_id AND p.id = pi.product_id AND pi.file_id = f.id AND f.content_type like '%배너이미지%' "
			+ "order by pi.type"; // 대표이미지 먼저 가져오도록
	final static String GET_PRODUCT_DETAIL = ""
			+ " SELECT p.name product_name, pd.content, di.observation_time, replace(left(di.display_start, 10), '-', '.') display_start, replace(left(di.display_end, 10), '-', '.') display_end, p.event, sales_end, sales_flag, di.place_name, di.place_lot, di.place_street, di.tel, di.homepage, di.email "
			+ " FROM product p, product_detail pd, display_info di "
			+ " WHERE p.id = :product_id AND p.id = pd.product_id AND p.id = di.product_id";
	final static String GET_PRODUCT_NOTICE_IMAGE = "SELECT f.file_name, f.save_file_name "
			+ "FROM product_image pi, file f "
			+ "WHERE pi.product_id = :product_id AND pi.file_id = f.id AND f.content_type like '%공지사항%' "
			+ "ORDER BY f.content_type";
	final static String GET_PRODUCT_INFO_IMAGE = "SELECT f.file_name, f.save_file_name "
			+ "FROM product_image pi, file f "
			+ "WHERE pi.product_id = :product_id AND pi.file_id = f.id AND f.content_type like '%공연정보%' "
			+ "ORDER BY f.content_type";
	
	final static String GET_RESERVE_INFO = "SELECT p.id product_id, p.name product_name, FORMAT(pp.price*(1-pp.discount_rate),0) minimum_price, f.file_name, f.save_file_name, di.place_name, di.place_street, di.place_lot, replace(left(di.display_start, 10), '-', '.') display_start, replace(left(di.display_end, 10), '-', '.') display_end, di.observation_time"
			+ " FROM product p INNER JOIN product_price pp ON p.id = pp.product_id"
			+ " LEFT JOIN product_image pi ON p.id = pi.product_id"
			+ " LEFT JOIN file f ON pi.file_id = f.id"
			+ " INNER JOIN display_info di ON p.id = di.product_id"
			+ " WHERE p.id = :product_id AND pi.type = 1"
			+ " ORDER BY pp.price*(1-pp.discount_rate)"
			+ " LIMIT 1";
	final static String GET_PRICE_INFO = "SELECT pp.price_type, pp.price, pp.discount_rate, pp.price*(1-pp.discount_rate) discount_price"
			+ " FROM product p INNER JOIN product_price pp ON p.id = pp.product_id"
			+ " WHERE p.id = :product_id";
}

