package connect.reservation.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import connect.reservation.dao.ReservationCommentDao;
import connect.reservation.dto.ReservationComment;
import connect.reservation.service.ReservationCommentService;

@Service
@Transactional
public class ReservationCommentServiceImpl implements ReservationCommentService{
	
	@Autowired
	ReservationCommentDao reservationCommentDao;
	
	public Map<String, Object> getList(int productId) {		
		Map<String, Object> map = new HashMap<String, Object>();
		List<ReservationComment> list = new ArrayList<ReservationComment>();
		
		list = reservationCommentDao.getCommentList(productId);
		double scoreAverage = 0;
		
		for(int i=0; i<list.size(); i++)
			scoreAverage += list.get(i).getScore();
		scoreAverage = scoreAverage/list.size();
		
		map.put("commentList", list);
		map.put("commentCount", list.size());
		map.put("scoreAverage", scoreAverage);
		map.put("starPoint", scoreAverage/5.0*100);
		
		return map;
	}
	
//	public List<ReservationComment> getImageList(int commentId) {
//		return reservationCommentDao.getImageList(commentId);
//	}
	
	public Map<String, Object> getImage(int commentId) {
		Map<String, Object> map = new HashMap<String, Object>();
		
		List<ReservationComment> list = reservationCommentDao.getImageList(commentId);
		map.put("count", list.size());
		map.put("imageList", list);
		
		return map;
	}
}
