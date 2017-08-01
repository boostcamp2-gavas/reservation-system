package connect.reservation.service;

import java.util.List;
import java.util.Map;

import connect.reservation.domain.Product;
import connect.reservation.domain.ReservationUserComment;
import connect.reservation.dto.ReservationComment;

public interface CommentService {
	List<ReservationComment> getList(int productId, int start, int end);
	public int getCount(int productId);
	public double getScoreAverage(int productId);
//	public List<ReservationComment> getImageList(int commentId);
	public List<ReservationComment> getImage(int commentId);
	public Product getName(int reservationId);
	public int add(ReservationUserComment reservationUserComment);
}
