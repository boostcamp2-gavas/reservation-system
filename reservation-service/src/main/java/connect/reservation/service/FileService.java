package connect.reservation.service;

import connect.reservation.domain.File;
import connect.reservation.domain.ReservationUserCommentImage;

public interface FileService {
	public int add(int commentId, File file);
}
