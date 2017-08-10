package com.gavas.dao.sqls;

public class UserSqls {
    public static final String SELECT_USER_BY_SNS_ID =
            "SELECT id, username, email, tel, nickname, sns_id, sns_type, sns_profile, admin_flag, create_date, modify_date" +
                    " FROM users WHERE sns_id = :snsId";
}
