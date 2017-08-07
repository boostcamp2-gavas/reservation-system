package kr.or.reservation.jdbc;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.List;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import kr.or.reservation.config.RootApplicationContextConfig;
import kr.or.reservation.dao.ReservationDao;
import kr.or.reservation.dto.ReservationDTO;
import kr.or.reservation.service.ReservationService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = RootApplicationContextConfig.class)
@Transactional
public class ReservationTest {

	@Autowired
	ReservationDao dao ;
	
	@Autowired
	ReservationService service;
	
	
	Logger log = Logger.getLogger(this.getClass());
	
	// dao test 
	@Test
	public void SelectOne() {
		// config
<<<<<<< HEAD
		int id  = 2;
		//ProductForDetail detail =dao.selectOne(id);
		ReservationDTO dto =dao.selectOne(id);
=======
		int productId  = 2;
		//ProductForDetail detail =dao.selectOne(id);
		ReservationDTO dto =dao.selectByProductId(productId);
>>>>>>> 0d95395487ea32084ee49af481f7933ef7c9a78a
		
		// test 
		log.info(dto.toString());
		
<<<<<<< HEAD
		assertThat(id, is(dto.getId()));
=======
		assertThat(productId, is(dto.getId()));
>>>>>>> 0d95395487ea32084ee49af481f7933ef7c9a78a
	}
	
	
	@Test
	public void SelectPrice() {
		// config
		int id  = 2;
		//ProductForDetail detail =dao.selectOne(id);
		List<?> dto =dao.selectPrice(id);
		
		// test 
		log.info(dto.toString());
		
	}
	
	@Test
	public void SelectReservation() {
<<<<<<< HEAD
		ReservationDTO dto = service.selectOne(2);
=======
		ReservationDTO dto = service.selectByProductId(2);
>>>>>>> 0d95395487ea32084ee49af481f7933ef7c9a78a
		log.info(dto.toString());
	}
	

}
