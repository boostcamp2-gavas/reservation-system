package com.gavas.dao.sqls;

public class ReservationSqls {
    public static final String SELECT_RESERVATION_BY_USER_ID =
            "SELECT RI.id, RI.product_id, RI.user_id, RI.general_ticket_count, RI.youth_ticket_count," +
                    " RI.child_ticket_count, RI.reservation_name, RI.reservation_tel, RI.reservation_email, RI.reservation_type," +
                    " DI.observation_time, DI.display_start, DI.display_end, DI.place_name, P.name" +
                    " FROM reservation_info AS RI" +
                    " JOIN display_info AS DI" +
                    " ON RI.product_id = DI.product_id" +
                    " JOIN product AS P" +
                    " ON RI.product_id = P.id" +
                    " WHERE RI.user_id = :id";

    public static final String UPDATE_RESERVATION_TYPE_BY_ID =
            "UPDATE reservation_info SET\n"
                    + "reservation_type = :reservationType, "
                    + "modify_date = CURRENT_TIMESTAMP() "
                    + "WHERE id = :id";
}
