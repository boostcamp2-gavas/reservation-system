package com.gavas.dao.sqls;

public class UserCommentSqls {
    public static final String SELECT_USER_COMMENT_ID_BY_ID =
            "SELECT id FROM reservation_user_comment WHERE id = :userCommentId";

    public static final String SELECT_TOTAL_COMMENTS_STATUS_BY_PRODUCT_ID =
            "SELECT product_id, count, avg FROM comment_score_status WHERE product_id = :productId";

    public static final String SELECT_USER_COMMENTS_BY_PRODUCT_ID =
            "SELECT UC.id, UC.product_id, UC.user_id, UC.score, UC.comment, UC.create_date, UC.modify_date, U.nickname," +
                    " MIN(UCI.file_id) AS file_id, count(UCI.id) AS file_count " +
                    "FROM reservation_user_comment AS UC " +
                    "LEFT JOIN reservation_user_comment_image AS UCI ON UC.id = UCI.reservation_user_comment_id " +
                    "JOIN users AS U ON UC.user_id = U.id " +
                    "GROUP BY uc.id HAVING UC.product_id = :productId AND UC.id > :commentId LIMIT :limit ";

    public static final String SELECT_FILE_ID_BY_USER_COMMENT_ID =
            "SELECT file_id FROM reservation_user_comment_image WHERE reservation_user_comment_id = :userCommentId";

}
