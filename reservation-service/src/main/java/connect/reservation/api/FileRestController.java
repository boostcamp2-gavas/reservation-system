package connect.reservation.api;

import java.io.FileInputStream;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import connect.reservation.service.FileService;

@RestController
@RequestMapping("/api/files")
public class FileRestController {

	private FileService fileService;
	
	@Autowired
	public FileRestController(FileService fileService) {
		this.fileService = fileService;
	}
	
	@GetMapping(path="/{id}")
	public void downloadReservationUserCommentImage(
            @PathVariable(name="id") int id,
            HttpServletResponse response) {

    	connect.reservation.domain.File imgFile = fileService.get(id);
        String originalFilename = imgFile.getFileName();
        String contentType = "image/jpeg";
        String saveFileName = imgFile.getSaveFileName();

        response.setHeader("Content-Disposition", "attachment; filename=\"" + originalFilename + "\";");
        response.setHeader("Content-Transfer-Encoding", "binary");
        response.setHeader("Content-Type", contentType);
        //response.setHeader("Content-Length", ""+ fileSize);
        response.setHeader("Pragma", "no-cache;");
        response.setHeader("Expires", "-1;");

        java.io.File readFile = new java.io.File(saveFileName);
        if(!readFile.exists()){ // 파일이 존재하지 않다면
            throw new RuntimeException("file not found");
        }

        FileInputStream fis = null;
        try{
            fis = new FileInputStream(readFile);
            FileCopyUtils.copy(fis, response.getOutputStream()); // 파일을 저장할때도 사용할 수 있다.
            response.getOutputStream().flush();
        }catch(Exception ex){
            throw new RuntimeException(ex);
        }finally {
            try {
                fis.close();
            }catch(Exception ex){
                // 아무것도 하지 않음 (필요한 로그를 남기는 정도의 작업만 함.)
            }
        }

    }
}
