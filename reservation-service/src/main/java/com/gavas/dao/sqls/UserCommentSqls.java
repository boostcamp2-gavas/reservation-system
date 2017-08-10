package com.gavas.dao.sqls;

public class UserCommentSqls {
    public static final String SELECT_TOTAL_COMMENT_STATUS_BY_PRODUCT_ID = "SELECT product_id, count, avg FROM comment_score_status WHERE product_id = :productId";

}
