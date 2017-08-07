package kgw.reservation.sql;

public class ProductPriceSqls {
	public final static String SELECT_PRICE_BY_PRODUCT_ID ="select"
			+ "											   price_type,"
			+ "											   price,"
			+ "											   discount_rate"
			+ "											   from product_price"
			+ "											   where product_id = :productId";
}
