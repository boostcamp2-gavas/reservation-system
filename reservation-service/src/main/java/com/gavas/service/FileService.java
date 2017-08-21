package com.gavas.service;

import com.gavas.domain.FileDomain;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface FileService {
    FileDomain getFileDomainByFileId(Long fileId);
    List<Integer> saveFiles(Long userId, MultipartFile[] files);
}
