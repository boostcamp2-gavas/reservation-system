package kr.or.reservation.jdbc;

import java.sql.Timestamp;
import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;
import static org.mockito.Matchers.*;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import kr.or.reservation.config.RootApplicationContextConfig;
import kr.or.reservation.domain.FileDomain;
import kr.or.reservation.service.ImgService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = RootApplicationContextConfig.class)
public class FileTest {
	
	@Autowired
	ImgService service;
	
	// array 를 DB에 insert
	@Test
	public void InsertFileTest() {
		FileDomain[] files = new FileDomain[2];
		files[0] = new FileDomain(15, "T",  "T", 20, 0,"jsp",new Timestamp(System.currentTimeMillis()),new Timestamp(System.currentTimeMillis()));
		files[1] = new FileDomain(15, "T",  "T", 20, 0,"jsp",new Timestamp(System.currentTimeMillis()),new Timestamp(System.currentTimeMillis()));
		
		Assert.assertThat(true, is(service.insertFileArray(files)));
	}

}
