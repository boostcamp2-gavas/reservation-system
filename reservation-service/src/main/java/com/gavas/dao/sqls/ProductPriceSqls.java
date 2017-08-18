package com.gavas.dao.sqls;

public class ProductPriceSqls {
    public static final String SELECT_PRODUCT_PRICE_BY_PRODUCT_ID =
            "SELECT id, product_id, price_type, price, discount_rate" +
                    " FROM product_price" +
                    " WHERE product_id = :id";
}
