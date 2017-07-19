package kr.or.reservation.service;

import java.util.List;

import kr.or.reservation.domain.AVGForComment;
import kr.or.reservation.domain.CommentForDetail;

public interface CommentForDetailService {

	public List<CommentForDetail> selectByProductId(int productId);
	public AVGForComment selectAvgScoreByProductId(int producId);
	public List<?> getFileIdByCommentId(int commentId);
}
