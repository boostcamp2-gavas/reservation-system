package connect.reservation.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import connect.reservation.dao.FileDao;
import connect.reservation.domain.File;
import connect.reservation.domain.ReservationUserCommentImage;
import connect.reservation.service.FileService;

@Service
public class FileServiceImpl implements FileService{
	private FileDao fileDao;
	
	@Autowired
	public void setFileDao (FileDao fileDao) {
		this.fileDao = fileDao;
	}
	
	@Override
	@Transactional
	public int add(int commentId, File file) {
		int fileId = fileDao.add(file);
		
		ReservationUserCommentImage commentImage = new ReservationUserCommentImage();
		commentImage.setReservation_user_comment_id(commentId);
		commentImage.setFile_id(fileId);
		
		return fileDao.addCommentImage(commentImage);
	}
	
	
}
