package kr.or.reservation.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.or.reservation.dao.CommentImageDao;
import kr.or.reservation.dao.FileDao;
import kr.or.reservation.dao.ImgDao;
import kr.or.reservation.domain.CommentImage;
import kr.or.reservation.domain.FileDomain;
import kr.or.reservation.dto.ImgDTO;
import kr.or.reservation.service.ImgService;

@Service
public class ImgServiceImpl implements ImgService {

	ImgDao imgDao;
	FileDao fileDao;
	CommentImageDao commentImageDao;
	
	@Autowired
	public void setImgDao(ImgDao imgDao) {
		this.imgDao = imgDao;
	}

	@Autowired
	public void setFileDao(FileDao fileDao) {
		this.fileDao = fileDao;
	}
	
	@Autowired
	public void setCommentImageDao(CommentImageDao commentImageDao) {
		this.commentImageDao = commentImageDao;
	}

	@Override
	public List<ImgDTO> selectList(int id) {
		// TODO Auto-generated method stub
		if (id > 0) {
			return imgDao.selectList(id);
		}
		return null;
	}

	@Override
	public ImgDTO selectOne(long fileId) {
		if (fileId > 0) {
			return imgDao.selectOne(fileId);
		}
		return null;
	}

	@Override
	public int[] insertFileArray(FileDomain[] files) {
		if (files == null) {
			return null;
		}
		return fileDao.insertArray(files);
	}

	@Override
	public boolean insertImageArray(int reservationUserCommentId, int[] fileId) {
		// TODO Auto-generated method stub
		int length = fileId.length;
		CommentImage[] commentImage = new CommentImage[length];
		for(int i=0; i<length; ++i) {
			commentImage[i] = new CommentImage(reservationUserCommentId,fileId[i]);
		}
		return commentImageDao.insertArray(commentImage)!=null;
		
	}

}
