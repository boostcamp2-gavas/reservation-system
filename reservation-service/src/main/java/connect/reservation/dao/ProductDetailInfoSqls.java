package connect.reservation.dao;

public class ProductDetailInfoSqls {
	final static String endNum = "10";
	
	final static String GET_PRODUCT_DETAIL = "SELECT pd.content, di.observation_time, di.display_start, di.display_end, p.event, sales_end, sales_flag, di.place_name, di.place_lot, di.place_street, di.tel, di.homepage, di.email "
			+ "FROM product p, product_detail pd, display_info di "
			+ "WHERE p.id = :product_id AND p.id = pd.product_id AND p.id = di.product_id";
	
			
}

