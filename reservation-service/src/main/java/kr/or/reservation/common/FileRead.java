package kr.or.reservation.common;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

import org.springframework.web.multipart.MultipartFile;

import kr.or.reservation.domain.FileDomain;

public class FileRead {
	// 파일 경로는 리눅스에 맞게 변경해야할듯.
	private static String baseDir = "c:" + File.separator + "temp" + File.separator; // c:\temp 디렉토리를 미리 만들어둔다.
		   
	public static FileDomain[] FileReader(int userId,MultipartFile[] files) {
		int fileLength = files.length;
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
              
            }//for
          
        } // if
        return fileArray;
	}
}
