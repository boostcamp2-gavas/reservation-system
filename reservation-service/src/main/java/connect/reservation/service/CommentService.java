package connect.reservation.service;

import java.util.Map;

import connect.reservation.domain.ReservationInfo;

public interface CommentService {
	public Map<String, Object> getList(int productId);
//	public List<ReservationComment> getImageList(int commentId);
	public Map<String, Object> getImage(int commentId);
	public String getName(int reservationId);
	public int add(ReservationInfo reservationInfo);
}
