package kr.or.reservation.sqls;

public class CategorySqls {
	// Query문을 별도의 class로 저장
	public final static String SELECT_BY_ID = "select * from category where id = :id";
	public final static String UPDATE_BY_ID = "update category set name = :name where id = :id";
	public final static String DELETE_BY_ID = "delete from category where id = :id";
	public final static String SELECT_ALL = "select * from category";
}
