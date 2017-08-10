package com.gavas.dao.sqls;

public class ProductSqls {

    public static final String SELECT_PRODUCT_ID_BY_ID =
            "SELECT id FROM product WHERE id = :productId";

    public static final String SELECT_PRODUCT_COUNT =
            "SELECT count(*) FROM product WHERE category_id = :categoryId";

    public static final String SELECT_PRODUCT_LIST_BY_CATEGORY_ID =
            "SELECT P.id, P.category_id, P.name, P.description, DI.place_name, PI.file_id" +
                    " FROM product AS P" +
                    " JOIN display_info AS DI ON P.id=DI.product_id " +
                    " JOIN product_image AS PI ON P.id = PI.product_id AND PI.type = 2" +
                    " WHERE P.category_id = :categoryId";

    public static final String SELECT_PRODUCT_DETAIL_BY_PRODUCT_ID =
            "SELECT p.id, p.name, p.description, p.sales_start, p.sales_end, p.event, di.place_name, p.sales_flag" +
                    " FROM product AS P" +
                    " JOIN display_info AS di ON p.id = di.product_id" +
                    " WHERE di.product_id = :productId";
}
