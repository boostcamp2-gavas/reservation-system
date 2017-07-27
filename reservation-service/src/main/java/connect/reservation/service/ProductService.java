package connect.reservation.service;

import java.util.List;
import java.util.Map;

import connect.reservation.dto.Product;

public interface ProductService {
	public int getProductCount();
	public int getCategoryProductCount(int categoryId);
	public Map<String, Object> getMainInfo(int start);
	public Map<String, Object> getCategoryInfo(int categoryId, int start);
	
	public List<Product> getImage(int productId);
	public Product getDetail(int productId);
	public List<Product> getNoticeImage(int productId);
	public List<Product> getInfoImage(int productId);
	
	public Map<String, Object> getReserveInfo(int productId) throws Exception;
	public List<Product> getPriceInfo(int productId);
	public int getMinimunPrice(List<Product> list);
}
