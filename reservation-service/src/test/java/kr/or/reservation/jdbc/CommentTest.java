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
import kr.or.reservation.domain.Comment;
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
	}
	
	@Test
	public void shouldSelectByProductId() {
		List<Comment> list= service.selectByProductId(2, 0, 10);
		log.info(list.toString());
		
		list= service.selectByProductId(2, 5, 10);
		log.info(list.toString());
	}
}
