package kr.or.reservation.service;

import java.util.List;
import java.util.Map;

import kr.or.reservation.domain.AVGForComment;
import kr.or.reservation.dto.CommentDTO;

public interface CommentService {

	public List<CommentDTO> selectByProductId(int productId);
	public AVGForComment selectAvgScoreByProductId(int producId);
	public List<?> getFileIdByCommentId(int commentId);
	public Long insert(CommentDTO comment);
}
