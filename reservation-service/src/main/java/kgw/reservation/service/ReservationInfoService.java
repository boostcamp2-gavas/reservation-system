package kgw.reservation.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kgw.reservation.dao.ReservationInfoDao;
import kgw.reservation.domain.ReservationInfo;

@Service
@Transactional(readOnly=false)
public class ReservationInfoService {
	private ReservationInfoDao reservationInfoDao;
	
	@Autowired
	public ReservationInfoService(ReservationInfoDao reservationInfoDao) {
		this.reservationInfoDao = reservationInfoDao;
	}
	
	public ReservationInfo create(ReservationInfo reservationInfo) {
		reservationInfo.setId(reservationInfoDao.insert(reservationInfo));
		return reservationInfo;
	}
}
