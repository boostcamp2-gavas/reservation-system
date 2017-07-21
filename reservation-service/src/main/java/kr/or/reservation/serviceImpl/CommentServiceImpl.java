package kr.or.reservation.serviceImpl;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.or.reservation.dao.CommentDao;
import kr.or.reservation.domain.AVGForComment;
import kr.or.reservation.dto.CommentDTO;
import kr.or.reservation.service.CommentService;

@Service
public class CommentServiceImpl implements CommentService{

	CommentDao dao ;
	Logger log = Logger.getLogger(this.getClass());
	@Autowired
	public CommentServiceImpl(CommentDao dao) {
		this.dao = dao;
	}
	
	@Override
	public List<CommentDTO> selectByProductId(int productId) {
		// TODO Auto-generated method stub
		return dao.selectByProductId(productId);
	}
	
	// 좀 복잡하긴한데 ..
	//java.math.BigDecimal cannot be cast to java.lang.Float  발생으로 type 을 String 으로 변경
	public AVGForComment selectAvgScoreByProductId(int producId) {
		Map<String, Object> map = dao.selectAvgScoreByProductId(producId).get(0);
		String score= (map.get("avg_score") ==null)? "0" : String.valueOf(map.get("avg_score"));
		Long count = (Long) map.get("amount_of_count");
		return new AVGForComment(count.intValue(),score);
	}

	@Override
	public List<?> getFileIdByCommentId(int commentId) {
		// TODO Auto-generated method stub
		return dao.getFileId(commentId);
	}
}
