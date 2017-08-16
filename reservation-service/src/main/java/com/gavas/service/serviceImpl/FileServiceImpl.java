package com.gavas.service.serviceImpl;

import com.gavas.dao.FileDao;
import com.gavas.domain.FileDomain;
import com.gavas.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FileServiceImpl implements FileService {
    FileDao fileDao;

    @Autowired
    public FileServiceImpl(FileDao fileDao){
        this.fileDao = fileDao;
    }

    @Override
    public FileDomain getFileDomaintByFileId(Long fileId) {
        return fileDao.selectFileDomainByfileId(fileId);
    }
}
