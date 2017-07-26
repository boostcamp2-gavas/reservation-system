package kr.or.reservation.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import kr.or.reservation.dao.UserReservationDao;
import kr.or.reservation.dto.UserReservationDTO;
import kr.or.reservation.service.UserReservationService;

public class UserReservationImpl implements UserReservationService {

	UserReservationDao userReservationDao;
	
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
	

}
