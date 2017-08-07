package kgw.reservation.controller.admin;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import kgw.reservation.service.FileService;

@Controller
@RequestMapping("/files")
public class FileController {
	private static final String DIRNAME ="/admin/files";
	@Value("${kgw.imageupload.path}")
    private String baseDir; // 이미지파일 다운로드할 기본 경로 
	private FileService fileService;
	
	@Autowired
	public FileController(FileService fileService) {
		this.fileService = fileService;
	}
    
    @GetMapping("/form")
    public String fileform(Model model){
    		model.addAttribute("url","/files");
        return DIRNAME+"/form";
    }
    @PostMapping
    public String create(@RequestParam("title") String title, @RequestParam("file") MultipartFile[] files) {
    	if(files != null && files.length > 0){

            //현재 날짜 string으로 반환
    		    String currentDate =  new SimpleDateFormat("yyyy" + File.separator + "MM" + File.separator + "dd").format(new Date());
            String formattedDate = baseDir + currentDate;
            File f = new File(formattedDate);
            if(!f.exists()){ // 파일이 존재하지 않는다면
                f.mkdirs(); // 해당 디렉토리를 만든다. 하위폴더까지 한꺼번에 만든다.
            }

            for(MultipartFile file : files) {
                String contentType = file.getContentType();
                String name = file.getName();
                String originalFilename = file.getOriginalFilename();
                long size = file.getSize();

                String uuid = UUID.randomUUID().toString();
                String saveFileName = formattedDate + File.separator + uuid; // 실제 저장되는 파일의 절대 경로

                // 실제 파일을 저장함.
                try(
                        InputStream in = file.getInputStream();
                        FileOutputStream fos = new FileOutputStream(saveFileName)){
                    int readCount = 0;
                    byte[] buffer = new byte[512];
                    while((readCount = in.read(buffer)) != -1){
                        fos.write(buffer,0,readCount);
                    }
                }catch(Exception ex){
                    ex.printStackTrace();
                }
                //File
                kgw.reservation.domain.File fileDomain = new kgw.reservation.domain.File();
                fileDomain.setContentType(contentType);
                fileDomain.setCreateDate(new Date());
                fileDomain.setDeleteFlag(0);
                fileDomain.setFileLength((int)size);
                fileDomain.setFileName(originalFilename);
                fileDomain.setSaveFileName(File.separator+currentDate+File.separator+uuid);
                // 해당부분은 로그인기능 구현후 추가 처리
                fileDomain.setUserId(5);
                
                fileService.create(fileDomain);
                
            } // for
        } // if
    	
    		return "redirect:files/form";
    }
    @GetMapping(path="/{id}")
    public void downloadReservationUserCommentImage(
            @PathVariable(name="id") Integer id,
            HttpServletResponse response
    ){
        // id를 이용하여 파일의 정보를 읽어온다.
        // 이 부분은 원래 db에서 읽어오는 것인데 db부분은 생략했다.
    		kgw.reservation.domain.File fileDomain = fileService.find(id);
        String originalFilename = fileDomain.getFileName();
        String contentType = fileDomain.getContentType();
        int fileSize = fileDomain.getFileLength();
        // 해당 파일이 이미 존재해야한다.
        String saveFileName = baseDir + fileDomain.getSaveFileName();

        response.setHeader("Content-Disposition", "attachment; filename=\"" + originalFilename + "\";");
        response.setHeader("Content-Transfer-Encoding", "binary");
        response.setHeader("Content-Type", contentType);
        response.setHeader("Content-Length", ""+ fileSize);
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
