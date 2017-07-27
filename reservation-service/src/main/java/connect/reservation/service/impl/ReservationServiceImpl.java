package connect.reservation.service.impl;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import connect.reservation.dao.ReservationDao;
import connect.reservation.domain.ReservationInfo;
import connect.reservation.dto.Reservation;
import connect.reservation.dto.ReservationCount;
import connect.reservation.service.ReservationService;

@Service
public class ReservationServiceImpl implements ReservationService{
	final static int General = 0;
	
	private ReservationDao reservationDao;
	
	@Autowired
	public void setReservationInfoDao(ReservationDao reservationDao) {
		this.reservationDao = reservationDao;
	}
	
	
	public Timestamp getDate(){
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return java.sql.Timestamp.valueOf(sdf.format(timestamp));
	}
	
	@Override
	@Transactional(readOnly = false)
	public int add(ReservationInfo reservationInfo) {		
		return reservationDao.insert(reservationInfo);
	}

	@Override
	public List<Reservation> get(int userId) {
		return reservationDao.select(userId);
	}

	@Override
	public List<ReservationCount> getCount(int userId) {
		return reservationDao.selectCount(userId);
	}
}
