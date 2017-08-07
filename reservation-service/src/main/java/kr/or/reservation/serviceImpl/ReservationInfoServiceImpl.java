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
<<<<<<< HEAD
	UserDao userDao;
	
	@Autowired
	protected void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}
=======
>>>>>>> 0d95395487ea32084ee49af481f7933ef7c9a78a

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
<<<<<<< HEAD
		int id = userDao.selectId(reservationInfo.getUserId());
		if(id <0) {
			return null;
		}
		reservationInfo.setUserId(id);
=======
>>>>>>> 0d95395487ea32084ee49af481f7933ef7c9a78a
		reservationInfo.setCreateDate(new Timestamp(System.currentTimeMillis()));
		reservationInfo.setReservationDate(new Timestamp(System.currentTimeMillis()));
		return reservationInfoDao.insert(reservationInfo);
	}
<<<<<<< HEAD
=======
	@Override
	public ReservationInfo selectById(int id) {
		if(id < 0) {
			return null;
		}
		return reservationInfoDao.selectById(id);
	}
	

>>>>>>> 0d95395487ea32084ee49af481f7933ef7c9a78a



}
