package connect.reservation.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import connect.reservation.dao.ReservationInfoDao;
import connect.reservation.domain.ReservationInfo;
import connect.reservation.service.ReservationInfoService;

@Service
public class ReservationInfoServiceImpl implements ReservationInfoService{
	private ReservationInfoDao reservationInfoDao;
	
	@Autowired
	public void setReservationInfoDao(ReservationInfoDao reservationInfoDao) {
		this.reservationInfoDao = reservationInfoDao;
	}
	
	
	public String getDate(){
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.format(date);
	}
	
	@Override
	@Transactional(readOnly = false)
	public int add(int productId, int userId, String countInfo, String name, String tel, String email, String reserveDate) {
		String count[] = countInfo.split("_");	
		ReservationInfo reservationInfo = new ReservationInfo();
		
		reservationInfo.setProductId(productId);
		reservationInfo.setUserId(userId);
		reservationInfo.setGeneralTicketCount(Integer.parseInt(count[0]));
		reservationInfo.setYouthTicketCount(Integer.parseInt(count[1]));
		reservationInfo.setChildTicketCount(Integer.parseInt(count[2]));
		reservationInfo.setReservationDate(reserveDate);
		reservationInfo.setCreateDate(getDate());
		
		return reservationInfoDao.insert(reservationInfo);
	}
}
