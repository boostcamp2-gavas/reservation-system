package kr.or.reservation.serviceImpl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.or.reservation.dao.UserDao;
import kr.or.reservation.dao.UserReservationDao;
import kr.or.reservation.dto.UserReservationDTO;
import kr.or.reservation.service.UserReservationService;


@Service
public class UserReservationImpl implements UserReservationService {

	Logger log = Logger.getLogger(this.getClass());
	
	UserReservationDao userReservationDao;
	UserDao userDao;
	
	
	@Autowired
	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	@Autowired
	public void setUserReservationDao(UserReservationDao userReservationDao) {
		this.userReservationDao = userReservationDao;
	}

	@Override
	public List<UserReservationDTO> selectAll(int userId) {
		// TODO Auto-generated method stub
		if(userId <= 0) {
			return null;
		}
		return userReservationDao.selectAll(userId);
	}
	
	/**
	 * getReservationType 
	 * 0 : 예정
	 * 1 : 확정
	 * 2 : 완료
	 * 3 : 취소
	 */
	@Override
	public Map<String,Integer> getTypeCount(List<UserReservationDTO> list){
		Map<String,Integer> lengthList =new HashMap<String,Integer>();
		int expectation = 0, end =0 ,decision = 0, cancellation=0;
		for(UserReservationDTO dto : list) {
			if(dto.getReservationType() == 0) {
				expectation++;
			}else if(dto.getReservationType() == 1) {
				decision++;
			}else if(dto.getReservationType() == 2) {
				end++;
			}else {
				cancellation++;
			}
		}
		lengthList.put("expectation", expectation);
		lengthList.put("decision", decision);
		lengthList.put("end", end);
		lengthList.put("cancellation", cancellation);
		return lengthList;
	}

	@Override
	public boolean cancelReservation(int userId,int reservationId) {
		if(userId<=0 || reservationId<=0) {
			return false;
		}
		log.info(" userID ::  "+userId + "  reservationId :: " +reservationId );
		return userReservationDao.cancelReservation(userId,reservationId);
	}

}
