package kr.or.reservation.sqls;

public class ImgSqls {
	public final static String SELECTBYPRODUCT_ID= "select img.product_id,file.id as file_id ,file.save_file_name,img.type from file inner join product_image  as img\r\n" + 
			"			on img.file_id = file.id  where img.product_id = :id; ";
	
	public final static String SELECTBYFILE_ID = "SELECT * FROM file where id = :id;";
}
