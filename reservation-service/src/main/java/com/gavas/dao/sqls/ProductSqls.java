package com.gavas.dao.sqls;

public class ProductSqls {
    public static final String SELECT_PRODUCT_LIST =
            "SELECT P.id, P.category_id, P.name, P.description, DI.place_name, PI.file_id" +
            " FROM product AS P" +
            " JOIN display_info AS DI ON P.id=DI.product_id " +
            " JOIN product_image AS PI ON P.id = PI.product_id AND PI.type = 2 WHERE P.id > :offsetId LIMIT 10";

    public static final String SELECT_PRODUCT_ID_BY_ID =
            "SELECT id FROM product WHERE id = :productId";

    public static final String SELECT_PRODUCT_COUNT =
            "SELECT count(*) FROM product";

    public static final String SELECT_PRODUCT_COUNT_BY_CATEGORY_ID =
            "SELECT count(*) FROM product WHERE category_id = :categoryId";

    public static final String SELECT_PRODUCT_LIST_BY_CATEGORY_ID =
            "SELECT P.id, P.category_id, P.name, P.description, DI.place_name, PI.file_id" +
                    " FROM product AS P" +
                    " JOIN display_info AS DI ON P.id=DI.product_id " +
                    " JOIN product_image AS PI ON P.id = PI.product_id AND PI.type = 2" +
                    " WHERE P.category_id = :categoryId AND P.id > :offsetId LIMIT 10";

    public static final String SELECT_PRODUCT_DETAIL_BY_PRODUCT_ID =
            "SELECT P.id, P.name, P.description, P.sales_start, P.sales_end, P.event," +
                    " DI.place_name, DI.place_lot, DI.place_street, DI.tel, DI.email, DI.homepage, P.sales_flag, PD.content" +
                    " FROM product AS P" +
                    " JOIN display_info AS DI ON P.id = DI.product_id " +
                    " JOIN product_detail AS PD ON P.id = PD.product_id" +
                    " WHERE DI.product_id = :productId";

    public static final String SELECT_PRODUCT_NAME_BY_PRODUCT_ID =
            "SELECT name FROM product WHERE id = :productId";

    public static final String SELECT_PRODUCT_RESERVE_INFO_BY_PRODUCT_ID =
            "SELECT P.id, P.name, P.description, P.sales_start, P.sales_end, DI.observation_time, DI.place_name, DI.display_start, DI.display_end, PI.file_id" +
                    " FROM product AS P" +
                    " JOIN display_info AS DI ON P.id = DI.product_id" +
                    " JOIN product_image AS PI ON P.id = PI.product_id" +
                    " WHERE PI.type = 2 AND P.id = :productId;";

    public static final String SELECT_PRODUCT_PRICE_INFO_BY_PRODUCT_ID =
            "SELECT price_type, price, discount_rate FROM product_price WHERE product_id = :productId ORDER BY price ASC;";
}
