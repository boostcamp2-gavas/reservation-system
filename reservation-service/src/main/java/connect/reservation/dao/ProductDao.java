package connect.reservation.dao;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import connect.reservation.dto.Product;



@Repository
public class ProductDao {
	private NamedParameterJdbcTemplate jdbc; // sql 을 실행하기 위해 사용되는 객체
    private SimpleJdbcInsert insertAction; // insert 를 편리하게 하기 위한 객체
    private RowMapper<Product> rowMapper = BeanPropertyRowMapper.newInstance(Product.class); // 칼럼 이름을 보통 user_name 과 같이 '_'를 활용하는데 자바는 낙타표기법을 사용한다 이것을 자동 맵핑한다.
    
    // Spring은 생성자를 통하여 주입을 하게 된다.
    public ProductDao(DataSource dataSource) {
        this.jdbc = new NamedParameterJdbcTemplate(dataSource); // Datasource를 주입
    }
    
    public int getProductCount() {
    	Map<String, Object> params = Collections.emptyMap();
    	return jdbc.queryForObject(ProductSqls.COUNT_PRODUCT, params, Integer.class);
    }
    
    public int getCategoryProductCount(int category_id) {
    	Map<String, Object> params = new HashMap<>();
        params.put("category_id", category_id);
        return jdbc.queryForObject(ProductSqls.COUNT_CATEGORY_PRODUCT, params, Integer.class);	
    }
    
    public List<Product> getMainInfo(int start) {
    	Map<String, Object> params = new HashMap<>();
        params.put("start", start);
    	return jdbc.query(ProductSqls.GET_MAIN_INFO, params, rowMapper);
    }
    
    public List<Product> getCategoryInfo(int category_id, int start) {
    	Map<String, Object> params = new HashMap<>();
    	params.put("category_id", category_id);
    	params.put("start", start);
    	return jdbc.query(ProductSqls.GET_CATEGORY_INFO, params, rowMapper);
    }
    
    public List<Product> getProductImage(int product_id) {
    	Map<String, Object> params = new HashMap<>();
    	params.put("product_id", product_id);
    	return jdbc.query(ProductSqls.GET_PRODUCT_IMAGE, params, rowMapper);
    }
    
    public Product getProductDetailInfo(int product_id) {
    	Map<String, Object> params = new HashMap<>();
        params.put("product_id", product_id);
    	return jdbc.queryForObject(ProductSqls.GET_PRODUCT_DETAIL, params, rowMapper);
    }
    
    public List<Product> getProductNoticeImage(int product_id) {
    	Map<String, Object> params = new HashMap<>();
    	params.put("product_id", product_id);
    	return jdbc.query(ProductSqls.GET_PRODUCT_NOTICE_IMAGE, params, rowMapper);
    }
    
    public List<Product> getProductInfoImage(int product_id) {
    	Map<String, Object> params = new HashMap<>();
    	params.put("product_id", product_id);
    	return jdbc.query(ProductSqls.GET_PRODUCT_INFO_IMAGE, params, rowMapper);
    }
    
    public Product getReserveInfo(int product_id) {
    	Map<String, Object> params = new HashMap<>();
    	params.put("product_id", product_id);
    	return jdbc.queryForObject(ProductSqls.GET_RESERVE_INFO, params, rowMapper);
    }
    
    public List<Product> getPriceInfo(int product_id) {
    	Map<String, Object> params = new HashMap<>();
    	params.put("product_id", product_id);
    	return jdbc.query(ProductSqls.GET_PRICE_INFO, params, rowMapper);
    }
}
