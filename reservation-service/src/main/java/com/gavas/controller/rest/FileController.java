package com.gavas.controller.rest;

import com.gavas.domain.FileDomain;
import com.gavas.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;

@RestController
@RequestMapping("/api/file")
@PropertySource("classpath:/application.properties")
public class FileController {
    @Value("${spring.resources.file-location}")
    private String downLoadBaseDir;

    private FileService fileService;

    @Autowired
    public FileController(FileService fileService){
        this.fileService = fileService;
    }

    @GetMapping("{fileId}")
    public void downloadReservationUserCommentImage(@PathVariable("fileId") Long fileId, HttpServletResponse response) {
        FileDomain fileLocation = fileService.getFileDomaintByFileId(fileId);

        String originalFilename = "원본파일명";
        String contentType = "image/jpeg";
        String saveFileName = fileLocation.getSaveFileName();

        response.setHeader("Content-Disposition", "attachment; filename=\"" + originalFilename + "\";");
        response.setHeader("Content-Transfer-Encoding", "binary");
        response.setHeader("Content-Type", contentType);
        response.setHeader("Pragma", "no-cache;");
        response.setHeader("Expires", "-1;");

        File readFile = new java.io.File(downLoadBaseDir + saveFileName);
        if (!readFile.exists()) {
            throw new RuntimeException("file not found");
        }

        try (FileInputStream fileInputStream = new FileInputStream(readFile)) {
            FileCopyUtils.copy(fileInputStream, response.getOutputStream());
            response.getOutputStream().flush();
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }
}
