package connect.reservation.service.impl;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import connect.reservation.dao.ReservationDao;
import connect.reservation.domain.ReservationInfo;
import connect.reservation.service.ReservationService;

@Service
public class ReservationServiceImpl implements ReservationService{
	private ReservationDao reservationDao;
	
	@Autowired
	public void setReservationInfoDao(ReservationDao reservationDao) {
		this.reservationDao = reservationDao;
	}
	
	
	public Timestamp getDate(){
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss");
		return java.sql.Timestamp.valueOf(sdf.format(timestamp));
	}
	
	@Override
	@Transactional(readOnly = false)
	public int add(int productId, int userId, String countInfo, String name, String tel, String email, Timestamp reserveDate) {
		String count[] = countInfo.split("-");	
		ReservationInfo reservationInfo = new ReservationInfo();
		
		reservationInfo.setProductId(productId);
		reservationInfo.setUserId(userId);
		reservationInfo.setGeneralTicketCount(Integer.parseInt(count[0]));
		reservationInfo.setYouthTicketCount(Integer.parseInt(count[1]));
		reservationInfo.setChildTicketCount(Integer.parseInt(count[2]));
		reservationInfo.setReservationName(name);
		reservationInfo.setReservationTel(tel);
		reservationInfo.setReservationEmail(email);
		reservationInfo.setReservationDate(reserveDate);
		reservationInfo.setCreateDate(getDate());
		
		for(int i=0; i<count.length; i++) {
			System.out.println(count[i]);
		}
		
		return reservationDao.insert(reservationInfo);
	}
}
