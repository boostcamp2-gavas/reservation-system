package connect.reservation.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import connect.reservation.dao.FileDao;
import connect.reservation.domain.File;
import connect.reservation.service.FileService;

@Service
public class FileServiceImpl implements FileService {
	
	private FileDao fileDao;
	
	@Autowired
	public FileServiceImpl(FileDao fileDao) {
		this.fileDao = fileDao;
	}
	
	@Override
	public File get(int id) {
		return fileDao.get(id);
	}
}
