package com.gavas.dao.sqls;

public class FileSqls {
    public static final String SELECT_FILE_ID_BY_PRODUCT_ID =
            "SELECT file_id FROM product_image WHERE product_id = :productId";

    public static final String SELECT_FILE_DOMAIN_BY_FILE_ID =
            "SELECT user_id, file_name, save_file_name, file_length, content_type, delete_flag, create_date, modify_date FROM file WHERE id = :fileId";
}
