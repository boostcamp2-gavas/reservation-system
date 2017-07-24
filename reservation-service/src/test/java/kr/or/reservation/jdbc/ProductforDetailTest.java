package kr.or.reservation.jdbc;

import java.sql.Timestamp;


import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import kr.or.reservation.config.RootApplicationContextConfig;
import kr.or.reservation.dao.ProductDao;
import kr.or.reservation.dto.ProductDetailDTO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = RootApplicationContextConfig.class)
@Transactional
public class ProductforDetailTest {

	@Autowired
	ProductDao dao ;
	
	Logger log = Logger.getLogger(this.getClass());
	
	
	@Test
	public void saleTimeTest() {
		ProductDetailDTO detail = dao.selectOne(2);
		 Timestamp t1 = new Timestamp(System.currentTimeMillis());
		 if(detail.getSalesEnd().getTime() - t1.getTime()  < 0) {
			 log.info("test");
		 }else {
			 
		 }
		 
	}
	
	

}
