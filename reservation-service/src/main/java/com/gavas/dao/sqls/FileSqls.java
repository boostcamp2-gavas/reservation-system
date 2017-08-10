package com.gavas.dao.sqls;

public class FileSqls {
    public static final String SELECT_FILE_ID_BY_PRODUCT_ID =
            "SELECT file_id FROM product_image WHERE product_id = :productId";
}
