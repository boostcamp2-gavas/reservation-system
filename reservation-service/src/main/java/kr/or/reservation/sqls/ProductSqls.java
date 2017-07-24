package kr.or.reservation.sqls;


public class ProductSqls {

	public static final String SELECT_BY_CATEGORY =  "select product.id ,  product.category_id , product.name,detail.content ,display.place_name, img.file_id " + 
			"from  product left outer join product_detail as detail " + 
			"	on product.id = detail.product_id " + 
			"left outer join display_info as display " + 
			"	on product.id = display.product_id " + 
			"left outer join  product_image as img  on product.id = img.product_id " + 
			"   inner join file " + 
			"	on file.id = img.file_id " + 
			"where product.category_id = :category_id and  img.type = 1 " + 
			"limit :start, 10";
	
	public static final String SELECT_ALL = "select product.id ,  product.category_id , product.name,detail.content ,display.place_name, img.file_id " + 
			" from product left outer join product_detail as detail " + 
			"	on product.id = detail.product_id " + 
			"left outer join display_info as display " + 
			"	on product.id = display.product_id " + 
			"left outer join product_image as img " + 
			"	on product.id = img.product_id " + 
			"inner join file  " + 
			"	on file.id = img.file_id " + 
			"where img.type = 1 " + 
			"limit :start , 10 ";
	
	public final static String SELECT_COUNT_ALL = "select count(*) from product";
	public final static String SELECT_COUNT_BY_CATEGORYID = "select count(*) from product where category_id = :category_id";
	

	public final static String SELECT_DETAIL_INFO = "select product.id, product.name,product.description, product.event ,product.sales_flag, product.sales_end, detail.content , display.homepage,display.email,display.tel , display.place_name,display.place_lot,display.place_street " + 
			"from product left outer join  product_detail detail " + 
			"	on product.id = detail.product_id " + 
			"left outer join display_info  display " + 
			"	on product.id = display.product_id "+ 
			"where product.id = :id ; ";
	
}	
