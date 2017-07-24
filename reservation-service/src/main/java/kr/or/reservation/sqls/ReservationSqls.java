package kr.or.reservation.sqls;

public class ReservationSqls {
	
	public static final String SELECT_ONE = "SELECT product.id , product.name ,product.sales_start , product.sales_end ,info.observation_time, info.place_lot " + 
			"FROM product LEFT OUTER JOIN display_info AS info " + 
			"ON product.id = info.product_id " + 
			"where product.id = :id;";
	public static final String SELECT_PRICE = "SELECT price_type,price,discount_rate FROM reservation.product_price where product_id = :id;";

}
