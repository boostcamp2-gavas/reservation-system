package connect.reservation.service;

import java.util.List;
import java.util.Map;

import connect.reservation.dto.ReservationComment;

public interface ReservationCommentService {
	public Map<String, Object> getCommentList(int productId);
//	public List<ReservationComment> getImageList(int commentId);
	public Map<String, Object> getImageList(int commentId);
}
