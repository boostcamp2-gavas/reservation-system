package com.gavas.dao.sqls;

public class ProductSqls {

    public static final String SELECT_PRODUCT_COUNT = "SELECT count(*) FROM PRODUCT WHERE category_id = :categoryId";
    public static final String SELECT_PRODUCT_LIST_BY_CATGORYID =
            "SELECT P.id, P.category_id, P.name, P.description, DI.place_name, PI.id" +
            " FROM product AS P" +
            " JOIN display_info AS DI ON P.id=DI.product_id " +
            " JOIN product_image AS PI ON P.id = PI.product_id AND PI.type = 2" +
            " WHERE P.category_id = :categoryId";
}
