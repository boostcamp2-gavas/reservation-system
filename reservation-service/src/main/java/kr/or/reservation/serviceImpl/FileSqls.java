package kr.or.reservation.serviceImpl;

public class FileSqls {
	public final static String INSERT = "INSERT INTO file " + 
			"(user_id,file_name,save_file_name,file_length,content_type,delete_flag,create_date,modify_date) " + 
			"VALUES (:userId,:fileName,:saveFileName,fileLength,contentsTye,deleteFlag,createDate,modifyDate);";

}
