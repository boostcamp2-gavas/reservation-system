package connect.reservation.dao;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import connect.reservation.config.RootApplicationContextConfig;
import connect.reservation.domain.ReservationType;
import connect.reservation.dto.Reservation;
import connect.reservation.dto.ReservationCount;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = RootApplicationContextConfig.class)
@Transactional
public class ReservationDaoTest {

	@Autowired
	private ReservationDao dao;
	
	@Test
	public void shouldSelect() {
		int userId = 10;
		List<Reservation> list = dao.select(userId);
		assertThat(list.size(), is(28));
		
	}
	
	@Test
	public void shouldSelectCount() {
		int userId = 10;
		List<ReservationCount> list = dao.selectCount(userId);
		assertThat(list.size(), is(4));
		int i = 0;
		for(ReservationCount count : list) {
			
			if(i == 0) {
				assertThat(count.getReservationType(), is(ReservationType.REQUESTING));
				assertThat(count.getCnt(), is(1));
			}
			i++;
		}
	}
	
	@Test
	public void shouldUpdate() {
		int id = 1;
		ReservationType type = ReservationType.REFUND_CANCEL;
		assertThat(dao.update(id, type.ordinal()), is(1));
	}
	
}
