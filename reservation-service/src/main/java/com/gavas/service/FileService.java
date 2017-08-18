package com.gavas.service;

import com.gavas.domain.FileDomain;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface FileService {
    FileDomain getFileDomaintByFileId(Long fileId);
    List<Integer> saveFiles(Integer userId, MultipartFile[] files);
}
