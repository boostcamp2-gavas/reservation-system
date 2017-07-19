package connect.reservation.service;

import java.util.List;
import java.util.Map;

import connect.reservation.dto.ProductInfo;

public interface ProductInfoService {
	public int getProductCount();
	public int getCategoryProductCount(int categoryId);
	// 메인페이지 전체 상품 목록
	public Map<String, Object> getMainInfo(int start);
	// 메인페이지 카테고리별 상품 목록
	public Map<String, Object> getCategoryInfo(int categoryId, int start);
	// 상세페이지 상품 이미지
	public List<ProductInfo> getProductImage(int productId);
	// 상세페이지 상품 정보
	public ProductInfo getProductDetail(int productId);
	// 상세페이지 공지사항 이미지
	public List<ProductInfo> getProductNoticeImage(int productId);
	// 상세페이지 공연정보 이미지
	public List<ProductInfo> getProductInfoImage(int productId);
}
