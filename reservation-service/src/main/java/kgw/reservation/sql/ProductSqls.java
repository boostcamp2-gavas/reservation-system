package kgw.reservation.sql;
public class ProductSqls {
	private final static String onSale = "1";
	private final static String mainImage = "1";
	public final static String SELECT_ALL_LIMIT ="select p.id, p.name, p.description, p.sales_end, f.id as fileId, f.save_file_name, d_i.place_name"
			+ "									 from product p"
			+ "									 left outer join display_info d_i  on p.id = d_i.product_id"
			+ "									 left outer join product_image p_i on p.id = p_i.product_id"
			+ "								     and p_i.type = "+mainImage
			+ "									 left outer join file f on p_i.file_id = f.id"
			+ " 									 where p.sales_flag ="+onSale
			+ "									 order by sales_end asc limit :offset, :size";
	
	public final static String COUNT_ALL = "select count(id) "
			+ "								from product p"
			+ "							   where p.sales_flag ="+onSale;
	
	public final static String SELECT_BY_CATEGORY_LIMIT = "select p.id, p.name, p.description, p.sales_end, f.id as fileId,  f.save_file_name, d_i.place_name"
			+ "												from product p"
			+ "												left outer join display_info d_i  on p.id = d_i.product_id"
			+ "												left outer join product_image p_i on p.id = p_i.product_id"
			+ "												and p_i.type = "+mainImage
			+ "												left outer join file f on p_i.file_id = f.id"
			+ "												where p.sales_flag = "+onSale+" and category_id = :categoryId"
			+ "												order by sales_end asc limit :offset, :size";

	
	public final static String COUNT_BY_CATEGORY = "select count(id) "
			+ "									   from product p"
			+ "									   where p.sales_flag ="+onSale+" and p.category_id = :categoryId";
	
	public final static String SELECT_PRODUCTDETAIL = "select "
			+ "										  p.name, "
			+ "										  p.description, "
			+ "										  p.event, "
			+ "										  p.sales_flag,"
			+ "										  p.sales_end, "
			+ "										  d_i.homepage, "
			+ "										  d_i.tel, "
			+ "										  d_i.email, "
			+ "										  d_i.place_name, "
			+ "										  d_i.place_lot, "
			+ "										  d_i.place_street, "
			+ "										  p_d.content"
			+ "										  from product p"  
			+ "										  left outer join product_detail p_d on p.id = p_d.product_id"  
			+ "    									  left outer join display_info d_i on p.id = d_i.product_id"
			+ "										  where p.id = :id";
	
	public final static String SELECT_PRODUCT_RESERVATION = "select "
			+ "												p.name,"
			+ "												d_i.place_name,"
			+ "												d_i.display_start,"
			+ "												d_i.display_end,"
			+ "												d_i.observation_time,"
			+ "												f.save_file_name"
			+ "												from product p"
			+ "												left outer join display_info d_i on p.id = d_i.product_id"
			+ "												left outer join product_image p_i on p.id = p_i.product_id and type ="+mainImage
			+ "												left outer join file f on p_i.file_id = f.id"
			+ "												where p.id = :id";
	}
