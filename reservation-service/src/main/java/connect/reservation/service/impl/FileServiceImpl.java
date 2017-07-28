package connect.reservation.service.impl;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import connect.reservation.dao.FileDao;
import connect.reservation.domain.File;
import connect.reservation.domain.ReservationUserCommentImage;
import connect.reservation.dto.UploadFile;
import connect.reservation.service.FileService;

@Service
public class FileServiceImpl implements FileService {
	
	private FileDao fileDao;
	
	@Autowired
	public FileServiceImpl(FileDao fileDao) {
		this.fileDao = fileDao;
	}
	
	
	public File get(int id) {
		return fileDao.get(id);
	}
	
	@Override
	@Transactional
	public void uploadFile(MultipartFile[] files, UploadFile uploadFile) {

        if(files != null && files.length > 0){

            // windows 사용자라면 "c:\temp\년도\월\일" 형태의 문자열을 구한다.
            String formattedDate = uploadFile.getPath() + java.io.File.separator + "uploadImg" + java.io.File.separator 
            		+ new SimpleDateFormat("yyyy" + java.io.File.separator + "MM" + java.io.File.separator + "dd").format(new Date());
           
            java.io.File f = new java.io.File(formattedDate);
            if(!f.exists()){ // 파일이 존재하지 않는다면
                f.mkdirs(); // 해당 디렉토리를 만든다. 하위폴더까지 한꺼번에 만든다.
            }

            for(MultipartFile file : files) {
                String contentType = file.getContentType();	// image/jpeg
                String name = file.getName();
                String originalFilename = file.getOriginalFilename();
                long size = file.getSize();

                String uuid = UUID.randomUUID().toString(); // 중복될 일이 거의 없다.
                String saveFileName = formattedDate + java.io.File.separator + uuid; // 실제 저장되는 파일의 절대 경로

            
                File fileDomain = new File();
                         
                fileDomain.setUserId(uploadFile.getUsreId());
                fileDomain.setFileName(originalFilename);
                fileDomain.setSaveFileName(saveFileName);
                fileDomain.setFileLength(size);
                fileDomain.setContentType(uploadFile.getContentType());
                fileDomain.setDeleteFlag(0);
                
                int fileId = fileDao.add(fileDomain);
                
                ReservationUserCommentImage commentImage = new ReservationUserCommentImage();
        		commentImage.setReservation_user_comment_id(uploadFile.getCommentId());
        		commentImage.setFile_id(fileId);
        		
        		fileDao.addCommentImage(commentImage);
        		

                // 실제 파일을 저장함.
                // try-with-resource 구문. close()를 할 필요가 없다. java 7 이상에서 가능
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
                
                
            } // for
        } // if
	}
}
