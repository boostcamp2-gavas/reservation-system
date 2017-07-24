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

import connect.reservation.dto.ProductInfo;



@Repository
public class ProductInfoDao {
	private NamedParameterJdbcTemplate jdbc; // sql 을 실행하기 위해 사용되는 객체
    private SimpleJdbcInsert insertAction; // insert 를 편리하게 하기 위한 객체
    private RowMapper<ProductInfo> rowMapper = BeanPropertyRowMapper.newInstance(ProductInfo.class); // 칼럼 이름을 보통 user_name 과 같이 '_'를 활용하는데 자바는 낙타표기법을 사용한다 이것을 자동 맵핑한다.
    
    // Spring은 생성자를 통하여 주입을 하게 된다.
    public ProductInfoDao(DataSource dataSource) {
        this.jdbc = new NamedParameterJdbcTemplate(dataSource); // Datasource를 주입
    }
    
    public int getProductCount() {
    	Map<String, Object> params = Collections.emptyMap();
    	return jdbc.queryForObject(ProductInfoSqls.COUNT_PRODUCT, params, Integer.class);
    }
    
    public int getCategoryProductCount(int category_id) {
    	Map<String, Object> params = new HashMap<>();
        params.put("category_id", category_id);
        return jdbc.queryForObject(ProductInfoSqls.COUNT_CATEGORY_PRODUCT, params, Integer.class);	
    }
    
    public List<ProductInfo> getMainInfo(int start) {
    	Map<String, Object> params = new HashMap<>();
        params.put("start", start);
    	return jdbc.query(ProductInfoSqls.GET_MAIN_INFO, params, rowMapper);
    }
    
    public List<ProductInfo> getCategoryInfo(int category_id, int start) {
    	Map<String, Object> params = new HashMap<>();
    	params.put("category_id", category_id);
    	params.put("start", start);
    	return jdbc.query(ProductInfoSqls.GET_CATEGORY_INFO, params, rowMapper);
    }
    
    public List<ProductInfo> getProductImage(int product_id) {
    	Map<String, Object> params = new HashMap<>();
    	params.put("product_id", product_id);
    	return jdbc.query(ProductInfoSqls.GET_PRODUCT_IMAGE, params, rowMapper);
    }
    
    public ProductInfo getProductDetailInfo(int product_id) {
    	Map<String, Object> params = new HashMap<>();
        params.put("product_id", product_id);
    	return jdbc.queryForObject(ProductInfoSqls.GET_PRODUCT_DETAIL, params, rowMapper);
    }
    
    public List<ProductInfo> getProductNoticeImage(int product_id) {
    	Map<String, Object> params = new HashMap<>();
    	params.put("product_id", product_id);
    	return jdbc.query(ProductInfoSqls.GET_PRODUCT_NOTICE_IMAGE, params, rowMapper);
    }
    
    public List<ProductInfo> getProductInfoImage(int product_id) {
    	Map<String, Object> params = new HashMap<>();
    	params.put("product_id", product_id);
    	return jdbc.query(ProductInfoSqls.GET_PRODUCT_INFO_IMAGE, params, rowMapper);
    }
    
    public ProductInfo getReserveInfo(int product_id) {
    	Map<String, Object> params = new HashMap<>();
    	params.put("product_id", product_id);
    	return jdbc.queryForObject(ProductInfoSqls.GET_RESERVE_INFO, params, rowMapper);
    }
    
    public List<ProductInfo> getPriceInfo(int product_id) {
    	Map<String, Object> params = new HashMap<>();
    	params.put("product_id", product_id);
    	return jdbc.query(ProductInfoSqls.GET_PRICE_INFO, params, rowMapper);
    }
}
