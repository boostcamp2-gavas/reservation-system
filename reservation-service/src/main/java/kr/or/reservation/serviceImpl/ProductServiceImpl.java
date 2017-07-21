package kr.or.reservation.serviceImpl;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.or.reservation.dao.ProductDao;
import kr.or.reservation.domain.Product;
import kr.or.reservation.dto.ProductDetailDTO;
import kr.or.reservation.service.ProductService;

@Service
@Transactional
public class ProductServiceImpl implements ProductService {

	ProductDao productDao;
	

	@Autowired
	public void setProductDao(ProductDao productDao) {
		this.productDao = productDao;
	}

	// 얘는 2가지 기능을 하고있음 
	@Override
	@Transactional(readOnly = true)
	public List<Product> getProductByCategory(int start, int categoryId) {
		// TODO Auto-generated method stub
		if(categoryId ==0) {
			return productDao.selectAll(start);
		}
		if(start >=0 && categoryId >=0) {
			return productDao.selectByCategory(start,categoryId);
		}
		return null;
	}

	@Override
	@Transactional(readOnly = true)
	public int countProduct(int categoryId) {
		if(categoryId >=0) {
			if(categoryId == 0) {
				return productDao.countCategoryAll();
			}else {
				return productDao.countCategory(categoryId);
			}
		}
		return 0;
		
	}
	
	@Override
	public ProductDetailDTO selectOne(int id) {
		// TODO Auto-generated method stub
		// 시간이 지낫을 경우, saleFlage를 3으로 두어, 판매 종료를 설정함.
		if( id >0) {
			ProductDetailDTO detail =productDao.selectOne(id);
			Timestamp t1 = new Timestamp(System.currentTimeMillis());
			if(detail.getSalesEnd().getTime() - t1.getTime()  < 0) {
				detail.setSalesFlag("3");
			}
			return detail;
		}
		return null;
	}
	


}
