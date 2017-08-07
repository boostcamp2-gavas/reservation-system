package connect.resrevation.service.impl;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import connect.reservation.config.RootApplicationContextConfig;
import connect.reservation.service.ProductService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = RootApplicationContextConfig.class)
@Transactional  // Transactional이 있을 때와 없을 때 각각 실행해보고 그 때마다 msyql에서 결과를 select해본다.
public class ProductInfoServiceImplTest {
	@Autowired
	ProductService productService;
	
	@Test
	public void shouldSelectAll() {		
		Map<String, Object> map = new HashMap<String, Object>();
		map = productService.getMainInfo(0);
		
//		for(int i=0; i<list.size(); i++) {
//			System.out.println(list.get(i).toString());
//		}
		
		int count = productService.getProductCount();
		
		assertThat(map.get("productCount"), is(count));
	}
	
	@Test
	public void shouldSelectCategoryProductCount() {	
		int categoryId = 1;
		
		Map<String, Object> map = new HashMap<String, Object>();
		map = productService.getCategoryInfo(categoryId, 0);
		
//		for(int i=0; i<list.size(); i++) {
//			System.out.println(list.get(i).toString());
//		}
		
		int count = productService.getCategoryProductCount(categoryId);
		
		if(count > 10)
			assertThat(map.get("productCount"), is(10));
		else
			assertThat(map.get("productCount"), is(count));
	}
}
