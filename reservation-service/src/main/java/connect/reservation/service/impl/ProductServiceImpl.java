package connect.reservation.service.impl;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import connect.reservation.dao.ProductDao;
import connect.reservation.dto.Product;
import connect.reservation.service.ProductService;

@Service
@Transactional
public class ProductServiceImpl implements ProductService {
	
	private ProductDao productDao;
	
	
	@Autowired
	private void setProductDao(ProductDao productDao) {
		this.productDao = productDao;
	}
	
	@Override
	public int getProductCount() {
		return productDao.getProductCount();
	}
	
	@Override
	public int getCategoryProductCount(int categoryId) {
		return productDao.getCategoryProductCount(categoryId);
	}
	
	@Override
	public Map<String, Object> getMainInfo(int start) {
		if(start < 0)
			return null;
		
		Map<String, Object> map = new HashMap<String, Object>();
		
		map.put("productList", productDao.getMainInfo(start));
		map.put("productCount", productDao.getProductCount());
	
		return map;
	}
	
	@Override
	public Map<String, Object> getCategoryInfo(int categoryId, int start) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("productList", productDao.getCategoryInfo(categoryId, start));
		map.put("productCount", productDao.getCategoryProductCount(categoryId));
	
		return map;
	}

	@Override
	public List<Product> getImage(int productId) {
		return productDao.getProductImage(productId);
	}
	
	@Override
	public Product getDetail(int productId) {
		return productDao.getProductDetailInfo(productId);
	}
	
	@Override
	public List<Product> getNoticeImage(int productId) {
		return productDao.getProductNoticeImage(productId);
	}
	
	@Override
	public List<Product> getInfoImage(int productId) {
		return productDao.getProductInfoImage(productId);
	}
	
	@Override
	public Map<String, Object> getReserveInfo(int productId) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		
		Product info = new Product();
		info = productDao.getReserveInfo(productId);
		
		map.put("info", info);
		map.put("startDay", getDateDay(info.getDisplayStart()));
		map.put("endDay", getDateDay(info.getDisplayEnd()));
		
		return map;
	}
	
	@Override
	public List<Product> getPriceInfo(int productId) {
		return productDao.getPriceInfo(productId);
	}
	
	public String getDateDay(Timestamp date) throws Exception {
	    String day = "" ;
	     
	    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy.MM.dd") ;
	    Date nDate = date;
	     
	    Calendar cal = Calendar.getInstance() ;
	    cal.setTime(nDate);
	     
	    int dayNum = cal.get(Calendar.DAY_OF_WEEK) ;
	     
	    switch(dayNum){
	        case 1:
	            day = "일";
	            break ;
	        case 2:
	            day = "월";
	            break ;
	        case 3:
	            day = "화";
	            break ;
	        case 4:
	            day = "수";
	            break ;
	        case 5:
	            day = "목";
	            break ;
	        case 6:
	            day = "금";
	            break ;
	        case 7:
	            day = "토";
	            break ;
	    }
	    return day ;
	}


}
