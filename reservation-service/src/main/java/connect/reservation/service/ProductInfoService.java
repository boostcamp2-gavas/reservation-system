package connect.reservation.service;

import java.util.List;
import java.util.Map;

import connect.reservation.dto.ProductInfo;

public interface ProductInfoService {
	public int getProductCount();
	public int getCategoryProductCount(int categoryId);
	public Map<String, Object> getMainInfo(int start);
	public Map<String, Object> getCategoryInfo(int categoryId, int start);
	
	public List<ProductInfo> getImage(int productId);
	public ProductInfo getDetail(int productId);
	public List<ProductInfo> getNoticeImage(int productId);
	public List<ProductInfo> getInfoImage(int productId);
	
	public Map<String, Object> getReserveInfo(int productId) throws Exception;
	public List<ProductInfo> getPriceInfo(int productId);
}
