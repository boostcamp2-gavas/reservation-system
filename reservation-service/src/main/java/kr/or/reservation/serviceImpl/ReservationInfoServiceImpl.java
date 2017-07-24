package kr.or.reservation.serviceImpl;

import java.sql.Timestamp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.or.reservation.dao.ReservationDao;
import kr.or.reservation.dao.ReservationInfoDao;
import kr.or.reservation.dao.UserDao;
import kr.or.reservation.domain.ReservationInfo;
import kr.or.reservation.service.ReservationInfoService;

@Service
public class ReservationInfoServiceImpl implements ReservationInfoService {

	ReservationInfoDao reservationInfoDao;
	UserDao userDao;
	
	@Autowired
	protected void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	@Autowired
	public void setReservationDao(ReservationInfoDao reservationInfoDao) {
		this.reservationInfoDao = reservationInfoDao;
	}

	@Override
	public Long insert(ReservationInfo reservationInfo) {
		// TODO Auto-generated method stub
		if(reservationInfo == null) {
			return null;
		}
		int id = userDao.selectId(reservationInfo.getUserId());
		if(id <0) {
			return null;
		}
		reservationInfo.setUserId(id);
		reservationInfo.setCreateDate(new Timestamp(System.currentTimeMillis()));
		reservationInfo.setReservationDate(new Timestamp(System.currentTimeMillis()));
		return reservationInfoDao.insert(reservationInfo);
	}



}
