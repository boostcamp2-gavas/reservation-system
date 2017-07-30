package kr.or.reservation.service;

import java.util.List;

import kr.or.reservation.domain.CommentImage;
import kr.or.reservation.domain.FileDomain;
import kr.or.reservation.dto.ImgDTO;

public interface ImgService {
	
	public List<ImgDTO> selectList(int id);
	public ImgDTO selectOne(long fileId);
	public int[] insertFileArray(FileDomain[] image);
	public boolean insertImageArray(int reservationUserCommentId,int[] fileId); 
}
