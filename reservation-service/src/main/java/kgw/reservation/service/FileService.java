package kgw.reservation.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kgw.reservation.dao.FileDao;
import kgw.reservation.domain.File;

@Service
public class FileService {
private FileDao fileDao;
	
	@Autowired
	public FileService(FileDao fileDao) {
		this.fileDao = fileDao;
	}
	
	public Integer create(File file) {
		return fileDao.insert(file);
	}
	
	public File find(Integer id) {
		return fileDao.selectById(id);
	}
}
