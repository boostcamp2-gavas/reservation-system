package kr.or.reservation.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import kr.or.reservation.domain.FileDomain;
import kr.or.reservation.service.ImgService;


@Controller
@RequestMapping("/img")
public class ImgFileController {

	
	ImgService imgService;
	Logger log = Logger.getLogger(this.getClass());
	
	// 파일 경로는 리눅스에 맞게 변경해야할듯.
	private String baseDir = "c:" + File.separator + "temp" + File.separator; // c:\temp 디렉토리를 미리 만들어둔다.
	   
	@Autowired
	public void setImgService(ImgService imgService) {
		this.imgService = imgService;
	}


    @GetMapping(path="/{id}")
    public void downloadReservationUserCommentImage(
            @PathVariable(name="id") long id,
            HttpServletResponse response
    ){
    	
    	
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
 

    @PostMapping
    public String create(
            @RequestParam("title") String title,
            @RequestParam("files") MultipartFile[] files,
            HttpSession session){
    	
    	log.info(title);
    	log.info(files.length);
    	int userId = (Integer)session.getAttribute("id");
    	int fileLength = files.length;
    	// 길이만큼 배열 생성 
    	FileDomain[] fileArray = new FileDomain[fileLength];
        if(files != null && fileLength > 0){

            // windows 사용자라면 "c:\temp\년도\월\일" 형태의 문자열을 구한다.
            String formattedDate = baseDir + new SimpleDateFormat("yyyy" + File.separator + "MM" + File.separator + "dd").format(new Date());
            File f = new File(formattedDate);
            if(!f.exists()){ // 파일이 존재하지 않는다면
                f.mkdirs(); // 해당 디렉토리를 만든다. 하위폴더까지 한꺼번에 만든다.
            }
            for(int i =0; i<fileLength; ++i) {
            	  String contentType = files[i].getContentType();
                  String name = files[i].getName();
                  String originalFilename = files[i].getOriginalFilename();
                  long size = files[i].getSize();

                  String uuid = UUID.randomUUID().toString(); // 중복될 일이 거의 없다.
                  String saveFileName = formattedDate + File.separator + uuid+".jpg"; // 실제 저장되는 파일의 절대 경로
                  
                 // 실제 파일 저장
                  //java try-with-resources 
                  // try(여기에 자원을 표기하면) , finally을 사용하지않아도 닫힘 .
                  try(
                          InputStream in = files[i].getInputStream();
                          FileOutputStream fos = new FileOutputStream(saveFileName)){
                      int readCount = 0;
                      byte[] buffer = new byte[512];
                      while((readCount = in.read(buffer)) != -1){
                          fos.write(buffer,0,readCount);
                      }
                  }catch(Exception ex){
                      ex.printStackTrace();
                  }
                  // image 초기화 
                  fileArray[i] = new FileDomain(userId,originalFilename,saveFileName,size,0,contentType,new Timestamp(System.currentTimeMillis()),new Timestamp(System.currentTimeMillis()));
                  // 아래에서 출력되는 결과는 모두 database에 저장되야 한다.
                  // pk 값은 자동으로 생성되도록 한다.
                  log.info("title :" + title);
                  log.info("contentType :" + contentType);
                  log.info("name :" + name);
                  log.info("originalFilename : " + originalFilename);
                  log.info("size : " + size);
                  log.info("saveFileName : " + saveFileName);
              
            }//for
            boolean bool  =imgService.insertFileArray(fileArray);
            log.info(bool);
        } // if

        return "redirect:/img";
    }
    
}