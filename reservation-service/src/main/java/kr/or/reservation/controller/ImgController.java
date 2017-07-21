package kr.or.reservation.controller;

import java.io.FileInputStream;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

import kr.or.reservation.service.ImgService;


@Controller
public class ImgController {

	ImgService imgService;
	
	
	@Autowired
	public void setImgService(ImgService imgService) {
		this.imgService = imgService;
	}


	//url 바꿀것
    @GetMapping(path="/{id}")
    public void downloadReservationUserCommentImage(
            @PathVariable(name="id") long id,
            HttpServletResponse response
    ){
        // id를 이용하여 파일의 정보를 읽어온다.
        // 이 부분은 원래 db에서 읽어오는 것인데 db부분은 생략했다.
    	String fileName = imgService.selectOne(id).getSaveFileName();
        String contentType = "image/jpeg";
        //int fileSize = 271621;

        //response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\";");
        response.setHeader("Content-Transfer-Encoding", "binary");
        response.setHeader("Content-Type", contentType);
        //response.setHeader("Content-Length", ""+ fileSize);
        response.setHeader("Pragma", "no-cache;");
        response.setHeader("Expires", "-1;");

        java.io.File readFile = new java.io.File(fileName);
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