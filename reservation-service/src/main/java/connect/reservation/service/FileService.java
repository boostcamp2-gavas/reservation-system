package connect.reservation.service;

import org.springframework.web.multipart.MultipartFile;

import connect.reservation.domain.File;
import connect.reservation.dto.UploadFile;

public interface FileService {
	public File get(int id);
	public void uploadFile(MultipartFile[] files, UploadFile uploadFile);
}
