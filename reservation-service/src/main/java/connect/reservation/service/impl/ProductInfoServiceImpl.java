package connect.reservation.service.impl;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import connect.reservation.dao.ProductInfoDao;
import connect.reservation.dto.ProductInfo;
import connect.reservation.service.ProductInfoService;

@Service
@Transactional
public class ProductInfoServiceImpl implements ProductInfoService {
	
	private ProductInfoDao productInfoDao;
	
	
	@Autowired
	private void setProductInfoDao(ProductInfoDao productInfoDao) {
		this.productInfoDao = productInfoDao;
	}
	
	@Override
	public int getProductCount() {
		return productInfoDao.getProductCount();
	}
	
	@Override
	public int getCategoryProductCount(int categoryId) {
		return productInfoDao.getCategoryProductCount(categoryId);
	}
	
	@Override
	public Map<String, Object> getMainInfo(int start) {
		if(start < 0)
			return null;
		
		Map<String, Object> map = new HashMap<String, Object>();
		
		map.put("productList", productInfoDao.getMainInfo(start));
		map.put("productCount", productInfoDao.getProductCount());
	
		return map;
	}
	
	@Override
	public Map<String, Object> getCategoryInfo(int categoryId, int start) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("productList", productInfoDao.getCategoryInfo(categoryId, start));
		map.put("productCount", productInfoDao.getCategoryProductCount(categoryId));
	
		return map;
	}

	@Override
	public List<ProductInfo> getImage(int productId) {
		return productInfoDao.getProductImage(productId);
	}
	
	@Override
	public ProductInfo getDetail(int productId) {
		return productInfoDao.getProductDetailInfo(productId);
	}
	
	@Override
	public List<ProductInfo> getNoticeImage(int productId) {
		return productInfoDao.getProductNoticeImage(productId);
	}
	
	@Override
	public List<ProductInfo> getInfoImage(int productId) {
		return productInfoDao.getProductInfoImage(productId);
	}
	
	@Override
	public Map<String, Object> getReserveInfo(int productId) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		
		ProductInfo info = new ProductInfo();
		info = productInfoDao.getReserveInfo(productId);
		
		map.put("info", info);
		map.put("startDay", getDateDay(info.getDisplayStart()));
		map.put("endDay", getDateDay(info.getDisplayEnd()));
		
		return map;
	}
	
	@Override
	public List<ProductInfo> getPriceInfo(int productId) {
		return productInfoDao.getPriceInfo(productId);
	}
	
	public String getDateDay(String date) throws Exception {
	    String day = "" ;
	     
	    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy.MM.dd") ;
	    Date nDate = dateFormat.parse(date) ;
	     
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
