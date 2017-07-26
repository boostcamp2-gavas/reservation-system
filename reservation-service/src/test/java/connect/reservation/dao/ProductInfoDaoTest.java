package connect.reservation.dao;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import connect.reservation.config.RootApplicationContextConfig;
import connect.reservation.dto.Product;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = RootApplicationContextConfig.class)
@Transactional  // Transactional이 있을 때와 없을 때 각각 실행해보고 그 때마다 msyql에서 결과를 select해본다.
public class ProductInfoDaoTest {
	@Autowired
	ProductDao productDao;
	
	@Test
	public void shouldSelectAll() {		
		List<Product> list = new ArrayList<Product>();
		list = productDao.getMainInfo(0);
		
//		for(int i=0; i<list.size(); i++) {
//			System.out.println(list.get(i).toString());
//		}
		
		int count = productDao.getProductCount();
		
		if(count > 10)
			assertThat(list.size(), is(10));
		else
			assertThat(list.size(), is(count));
	}
	
	@Test
	public void shouldCountCategoryProduct() {
		int cnt = productDao.getCategoryProductCount(1);
		
		assertNotNull(cnt);
	}
	
	@Test
	public void shouldSelectCategoryProduct() {
		int categoryId = 1;
		List<Product> list = new ArrayList<Product>();
		list = productDao.getCategoryInfo(categoryId, 0);
		
//		for(int i=0; i<list.size(); i++) {
//			System.out.println(list.get(i).toString());
//		}
		
		int count = productDao.getCategoryProductCount(categoryId);
		
		if(count > 10)
			assertThat(list.size(), is(10));
		else
			assertThat(list.size(), is(count));
	}

}
