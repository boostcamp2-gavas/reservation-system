package kgw.reservation.sql;

public class CategorySqls {
	public final static String SELECT_BY_ID = "select id, name from category where id = :id";
	public final static String SELECT_BY_NAME ="select id from category where name = :name";
    public final static String DELETE_BY_ID = "delete from category where id = :id";
    public final static String UPDATE_BY_ID = "update category set name = :name where id = :id";
    public final static String SELECT_ALL = "select id, name from category";
}
