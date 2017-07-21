package kr.or.reservation.sql;


public class ProductSqls {
	// 보기편하게  String 을 이용하여 + 로 설정  
	/*
	 * 
	select product.id ,  product.category_id , product.name,detail.content ,display.place_name, img.save_file_name
	from  product left outer join product_detail as detail 
			on product.id = detail.product_id 
		left outer join display_info as display 
			on product.id = display.product_id 
		left outer join 
		( select save_file_name,product_id from file left outer join product_image 
		on file.id = product_image.file_id ) as img
	    	on product.id = img.product_id  
	where product.category_id = 1
	limit 0 , 4
	 */
	public static final String SELECT_BY_CATEGORY = "select product.id ,  product.category_id , product.name,detail.content ,display.place_name, img.file_id " + 
			"from  product left outer join product_detail as detail " + 
			"	on product.id = detail.product_id " + 
			"left outer join display_info as display " + 
			"	on product.id = display.product_id " + 
			"left outer join " + 
			"( select file.id as file_id ,product_id from file left outer join product_image " + 
			"	on file.id = product_image.file_id  where product_image.type = 1) as img" + 
			"    on product.id = img.product_id  " + 
			"where product.category_id = :category_id " + 
			"limit :start , 10";
	public static final String SELECT_ALL = "select product.id ,  product.category_id , product.name,detail.content ,display.place_name, img.file_id " + 
			"from  product left outer join product_detail as detail " + 
			"	on product.id = detail.product_id " + 
			"left outer join display_info as display " + 
			"	on product.id = display.product_id " + 
			"left outer join " + 
			"( select file.id as file_id, product_id from file left outer join product_image " + 
			"	on file.id = product_image.file_id  where product_image.type = 1) as img" + 
			"    on product.id = img.product_id  " + 
			"limit :start , 10";
	
	public final static String SELECT_COUNT_ALL = "select count(*) from product";
	public final static String SELECT_COUNT_BY_CATEGORYID = "select count(*) from product where category_id = :category_id";
	

	public final static String SELECT_DETAIL_INFO = "select product.id, product.name,product.description, product.event ,product.sales_flag, product.sales_end, detail.content , display.homepage,display.email,display.tel , display.place_name,display.place_lot,display.place_street " + 
			"from product left outer join  product_detail detail " + 
			"	on product.id = detail.product_id " + 
			"left outer join display_info  display " + 
			"	on product.id = display.product_id "+ 
			"where product.id = :id ; ";
	
}	
