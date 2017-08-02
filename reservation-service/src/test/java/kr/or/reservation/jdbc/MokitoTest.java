package kr.or.reservation.jdbc;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;
import static org.mockito.Matchers.*;
import static org.mockito.Mockito.*;

import java.util.List;

import org.apache.log4j.Logger;
import org.junit.Test;

import kr.or.reservation.dto.UserReservationDTO;

public class MokitoTest {
	Logger log = Logger.getLogger(this.getClass());
	
	
	@Test
	public void MokiTest() {
		// mock
		List mockedList = mock(List.class);

		// mock 사용하기
		mockedList.add("one");
		mockedList.clear();

	}
	
	@Test
	public void toStringbuilderTest() {
		UserReservationDTO dto = new UserReservationDTO();
		dto.setId((long)4);
		log.info(dto.toString());
	}
}
