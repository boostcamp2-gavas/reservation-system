package connect.reservation.service;

import java.sql.Timestamp;
import java.util.List;

import connect.reservation.dto.Reservation;
import connect.reservation.dto.ReservationCount;

public interface ReservationService {
	public int add(int productId, int userId, String countInfo, String name, String tel, String email, Timestamp reserveDate);
	public List<Reservation> get(int userId);
	public List<ReservationCount> getCount(int userId);
}
