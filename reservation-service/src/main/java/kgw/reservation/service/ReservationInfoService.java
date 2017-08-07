package kgw.reservation.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kgw.reservation.dao.ReservationInfoDao;
import kgw.reservation.domain.ReservationInfo;
import kgw.reservation.dto.MyReservation;
import kgw.reservation.dto.MyReservationCount;
import kgw.reservation.sql.ReservationInfoSqls;

@Service
public class ReservationInfoService {
	private ReservationInfoDao reservationInfoDao;	
	
	@Autowired
	public ReservationInfoService(ReservationInfoDao reservationInfoDao) {
		this.reservationInfoDao = reservationInfoDao;
	}
	@Transactional(readOnly=false)
	public ReservationInfo create(ReservationInfo reservationInfo) {
		Date date = new Date();
		
		reservationInfo.setCreateDate(date);
		reservationInfo.setModifyDate(date);

		reservationInfo.setId(reservationInfoDao.insert(reservationInfo));
		return reservationInfo;
	}
	@Transactional(readOnly=true)
	public List<MyReservation> findAllReservationByUserId(Integer userId) {
		return reservationInfoDao.selectByUserId(userId);
	}
	@Transactional(readOnly=true)
	public List<MyReservation> findReservationByUserIdAndType(Integer userId, Integer type) {
		return reservationInfoDao.selectByUserIdAndType(userId, type);
	}
	@Transactional(readOnly=true)
	public MyReservationCount countAllReservationByUserId(Integer userId) {
		List<MyReservation> myReservation = reservationInfoDao.selectByUserId(userId);
		MyReservationCount myReservationCount = new MyReservationCount();
		int schduleCount = 0;
		int completionCount = 0;
		int cancelCount = 0;
		int refundCount = 0;
		int etc = 0;
		
		if(myReservation!=null) {
			for( MyReservation item : myReservation) {
				Integer type = item.getReservationType();
				
				if(type==ReservationInfoSqls.ASKING || type==ReservationInfoSqls.CONFIRM) {
					schduleCount++;
				} else if (type == ReservationInfoSqls.COMPLETION) {
					completionCount++;
				} else if (type == ReservationInfoSqls.CANCELLATION) {
					cancelCount++;
				} else if (type == ReservationInfoSqls.REFUND ) {
					refundCount++;
				} else {
					etc++;
				}
			}
			myReservationCount.setSchedule(schduleCount);
			myReservationCount.setCompletion(completionCount);
			myReservationCount.setCancellationAndRefund(cancelCount+refundCount);
			myReservationCount.setTotal(schduleCount + completionCount + cancelCount+ refundCount+ etc);
		} else {
			myReservationCount.setSchedule(0);
			myReservationCount.setCompletion(0);
			myReservationCount.setCancellationAndRefund(0);
			myReservationCount.setTotal(0);
		}
		return myReservationCount;
	}
	@Transactional(readOnly=false)
	public Integer removeReservationById(Integer type) {
		return reservationInfoDao.deleteByType(type);
	}
	@Transactional(readOnly=false)
	public Integer modifyTypeById(Integer id, Integer type) {
		return reservationInfoDao.updateTypeById(id, type);
	}
	@Transactional(readOnly=true)
	public Integer findCountByUserIdAndProductId(Integer productId, Integer userId) {
		return reservationInfoDao.selectCountByUserIdAndProductId(productId, userId);
	}
	
}
