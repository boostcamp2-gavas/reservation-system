package connect.reservation.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import connect.reservation.dao.CommentDao;
import connect.reservation.domain.ReservationInfo;
import connect.reservation.dto.ReservationComment;
import connect.reservation.service.CommentService;

@Service
@Transactional
public class CommentServiceImpl implements CommentService{
	
	@Autowired
	CommentDao commentDao;
	
	@Override
	public Map<String, Object> getList(int productId) {		
		Map<String, Object> map = new HashMap<String, Object>();
		List<ReservationComment> list = new ArrayList<ReservationComment>();
		
		list = commentDao.getCommentList(productId);
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
	
	@Override
	public Map<String, Object> getImage(int commentId) {
		Map<String, Object> map = new HashMap<String, Object>();
		
		List<ReservationComment> list = commentDao.getImageList(commentId);
		map.put("count", list.size());
		map.put("imageList", list);
		
		return map;
	}
	
	@Override
	public String getName(int reservationId) {
		return commentDao.getReservationName(reservationId);
	}
	
	@Override
	@Transactional(readOnly = false)
	public int add(ReservationInfo reservationInfo) {
		return commentDao.add(reservationInfo);
	}
}
