package kr.or.reservation.serviceImpl;

import java.math.BigDecimal;
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
		if(productId > 0) {
			return dao.selectByProductId(productId);
		}
		return null;
	}
	

	public AVGForComment selectAvgScoreByProductId(int producId) {
		if(producId>0) {
			Long count =AVGForComment.getCount(producId);
			Float AVG = AVGForComment.getAVG(producId);
			if(count ==null || AVG ==null) {
				Map<String, Object> map = dao.selectAvgScoreByProductId(producId).get(0);
				// null check
				AVG= (map.get("avg_score") ==null)? 0 : ((BigDecimal)map.get("avg_score")).floatValue();
				count = (map.get("avg_score") ==null)? 0 : (Long)map.get("amount_of_count");
				AVGForComment.setCount(producId, count);
				AVGForComment.setAVG(producId, AVG);
				return new AVGForComment(count,AVG);
			}else {
				return new AVGForComment(count,AVG);
			}
			
		}
		return null;
	}

	@Override
	public List<?> getFileIdByCommentId(int commentId) {
		// TODO Auto-generated method stub
		if(commentId>0) {
			return dao.getFileId(commentId);
		}
		return null;
	}
}
