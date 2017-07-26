package kr.or.reservation.jdbc;

import java.util.List;
import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;
import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import kr.or.reservation.config.RootApplicationContextConfig;
import kr.or.reservation.dao.UserReservationDao;
import kr.or.reservation.dto.UserReservationDTO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = RootApplicationContextConfig.class)

public class UserReservationTest {
	
	@Autowired
	UserReservationDao dao;
	
	Logger log = Logger.getLogger(this.getClass());
	
	
	// 데이터를 가져오는지 테스트 
	@Test
	public void select() {
		int userId = 15;
		
		List<UserReservationDTO> list = dao.selectReservationByType(15,0);
		log.info(list.toString());
	}
	
	@Test
	public void update() {
		boolean value=  dao.cancelReservation(15,2);
		Assert.assertThat(value, is(true));
	}

}
