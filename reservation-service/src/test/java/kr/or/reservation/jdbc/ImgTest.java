package kr.or.reservation.jdbc;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import kr.or.reservation.config.RootApplicationContextConfig;
import kr.or.reservation.dao.ImgDao;
import kr.or.reservation.dao.ProductForDetailDao;
import kr.or.reservation.domain.ProductForDetail;
import kr.or.reservation.service.ProductForDetailService;

import static org.hamcrest.CoreMatchers.is;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.log4j.Logger;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = RootApplicationContextConfig.class)
@Transactional
public class ImgTest {

	@Autowired
	ImgDao dao ;
	
	
	
	Logger log = Logger.getLogger(this.getClass());
	
	// dao test 
	@Test
	public void SelectOne() {
		// config
		int id  = 1 ;
		//ProductForDetail detail =dao.selectOne(id);
		List<?> list =dao.selectList(id);
		
		// test 
		log.info(list.toString());
	}
	
	

}
