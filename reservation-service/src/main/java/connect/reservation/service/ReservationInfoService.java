package connect.reservation.service;

public interface ReservationInfoService {
	public int add(int productId, int userId, String countInfo, String name, String tel, String email, String reserveDate);
}
