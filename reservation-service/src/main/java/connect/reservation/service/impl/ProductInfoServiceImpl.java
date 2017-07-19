package connect.reservation.service.impl;

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
	@Autowired
	ProductInfoDao productInfoDao;
	
	
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
	public List<ProductInfo> getProductImage(int productId) {
		return productInfoDao.getProductImage(productId);
	}
	
	@Override
	public ProductInfo getProductDetail(int productId) {
		return productInfoDao.getProductDetailInfo(productId);
	}
	
	@Override
	public List<ProductInfo> getProductNoticeImage(int productId) {
		return productInfoDao.getProductNoticeImage(productId);
	}
	
	@Override
	public List<ProductInfo> getProductInfoImage(int productId) {
		return productInfoDao.getProductInfoImage(productId);
	}
}
