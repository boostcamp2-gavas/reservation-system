package connect.reservation.service;

import java.sql.Timestamp;

public interface ReservationService {
	public int add(int productId, int userId, String countInfo, String name, String tel, String email, Timestamp reserveDate);
}
