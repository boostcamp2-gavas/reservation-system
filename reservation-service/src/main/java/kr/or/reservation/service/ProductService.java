package kr.or.reservation.service;

import java.util.List;

import kr.or.reservation.domain.Product;
import kr.or.reservation.dto.ProductDetailDTO;

public interface ProductService {
	
	public List<Product> getProductByCategory(int start,int categoryId);
	public int countProduct(int categoryId);
	public ProductDetailDTO selectOne(int id);
}
