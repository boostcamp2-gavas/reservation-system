package kr.or.reservation.jdbc;

import java.util.List;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import kr.or.reservation.config.RootApplicationContextConfig;
<<<<<<< HEAD:reservation-service/src/test/java/kr/or/reservation/jdbc/CommentForDetailTest.java
import kr.or.reservation.dao.CommentDao;
=======
import kr.or.reservation.domain.Comment;
>>>>>>> 0d95395487ea32084ee49af481f7933ef7c9a78a:reservation-service/src/test/java/kr/or/reservation/jdbc/CommentTest.java
import kr.or.reservation.service.CommentService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = RootApplicationContextConfig.class)
@Transactional
public class CommentTest {

	@Autowired
	CommentService service;
	
	Logger log = Logger.getLogger(this.getClass());
	@Test
	public void seletAll() {
		
		//List<?> list= service.selectByProductId(2);
		// 가져 오는지 테스트 
		//log.info(service.selectAvgScoreByProductId(3));
	}
	
	@Test
	public void selectFileIdByCommentId() {
		
		List<?> list= service.getFileIdByCommentId(1);
		// 가져 오는지 테스트 
		log.info(list.toString());
<<<<<<< HEAD:reservation-service/src/test/java/kr/or/reservation/jdbc/CommentForDetailTest.java
=======
	}
	
	@Test
	public void shouldSelectByProductId() {
		List<Comment> list= service.selectByProductId(2, 0, 10);
		log.info(list.toString());
		
		list= service.selectByProductId(2, 5, 10);
		log.info(list.toString());
>>>>>>> 0d95395487ea32084ee49af481f7933ef7c9a78a:reservation-service/src/test/java/kr/or/reservation/jdbc/CommentTest.java
	}
}
