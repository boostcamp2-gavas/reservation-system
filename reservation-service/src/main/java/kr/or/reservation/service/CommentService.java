package kr.or.reservation.service;

import java.util.List;

import kr.or.reservation.domain.AVGForComment;
<<<<<<< HEAD
import kr.or.reservation.dto.CommentDTO;

public interface CommentService {

	public List<CommentDTO> selectByProductId(int productId);
	public AVGForComment selectAvgScoreByProductId(int producId);
	public List<?> getFileIdByCommentId(int commentId);
=======
import kr.or.reservation.domain.Comment;

public interface CommentService {

	public List<Comment> selectByProductId(int productId);
	public AVGForComment selectAvgScoreByProductId(int producId);
	public List<?> getFileIdByCommentId(int commentId);
	public Long insert(Comment comment);
	
	public boolean updateFileName(int commentId, int fileId,int fileLength);
	
	public List<Comment> selectByProductId(int productId, int start, int amount);
>>>>>>> 0d95395487ea32084ee49af481f7933ef7c9a78a
}
