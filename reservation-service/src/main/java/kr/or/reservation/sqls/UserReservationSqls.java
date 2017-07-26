package kr.or.reservation.sqls;

public class UserReservationSqls {
	// 가격 부분은 어떻게 가져올 것인지 조금 생각해볼것 .
	// 모든 테이블을 inner join으로 작성.
	public static final String selectAll = "SELECT product.id,product.name,display.display_start,display.display_end, info.general_ticket_count,info.youth_ticket_count,info.child_ticket_count " + 
			"FROM reservation.reservation_info as info inner join product " + 
			"on info.product_id = product.id " + 
			"inner join display_info as display " + 
			"on display.product_id = product.id " + 
			"where info.user_id = :id ";

}
