package kgw.reservation.dao;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.Date;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import kgw.reservation.config.RootApplicationContextConfig;
import kgw.reservation.domain.ReservationInfo;
import kgw.reservation.sql.ReservationInfoSqls;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = RootApplicationContextConfig.class)
@Transactional
public class ReservationInfoDaoTest {
	@Autowired
	private ReservationInfoDao reservationInfoDao;
	
	private ReservationInfo reservationInfo;
	private final Logger log = LoggerFactory.getLogger(ReservationInfoDaoTest.class);
	
	public ReservationInfoDaoTest() {
		
	}

	@Before
	public void setUp() throws Exception {
		this.reservationInfo = new ReservationInfo();
		this.reservationInfo.setProductId(27);
		this.reservationInfo.setUserId(1);
		this.reservationInfo.setGeneralTicketCount(1);
		this.reservationInfo.setYouthTicketCount(1);
		this.reservationInfo.setChildTicketCount(2);
		this.reservationInfo.setReservationName("리정연");
		this.reservationInfo.setReservationTel("01023232323");
		this.reservationInfo.setReservationEmail("abc@naver.com");
		this.reservationInfo.setReservationType(ReservationInfoSqls.ASKING);
		this.reservationInfo.setReservationDate(new Date());
		this.reservationInfo.setCreateDate(new Date());
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void shouldInsertAndSelect() {
		int id = reservationInfoDao.insert(this.reservationInfo);
		
		ReservationInfo selected = reservationInfoDao.selectById(id);
		
		assertThat(selected.getProductId(), is(reservationInfo.getProductId()));
		assertThat(selected.getUserId(), is(reservationInfo.getUserId()));
	}

}
