package kr.or.reservation.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.or.reservation.dao.ReservationDao;
import kr.or.reservation.dao.ReservationInfoDao;
import kr.or.reservation.domain.ReservationInfo;
import kr.or.reservation.service.ReservationInfoService;

@Service
public class ReservationInfoServiceImpl implements ReservationInfoService {

	ReservationInfoDao reservationInfoDao;
	
	
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
		return reservationInfoDao.insert(reservationInfo);
	}



}
