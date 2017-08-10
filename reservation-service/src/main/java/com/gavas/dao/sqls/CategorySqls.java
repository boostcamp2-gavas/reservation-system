package com.gavas.dao.sqls;

public class CategorySqls {
    public static final String SELECT_CATEGORY_BY_ID = "SELECT id,name FROM category WHERE id=:id";
    public static final String SELECT_CATEGORY_LIST = "SELECT id,name FROM category";
}
