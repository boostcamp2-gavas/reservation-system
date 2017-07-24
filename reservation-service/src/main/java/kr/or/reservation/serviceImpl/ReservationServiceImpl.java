package kr.or.reservation.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.or.reservation.dao.ReservationDao;
import kr.or.reservation.dto.ReservationDTO;
import kr.or.reservation.service.ReservationService;

@Service
public class ReservationServiceImpl implements ReservationService {

	ReservationDao reservationDao;
	
	@Autowired
	public void setReservationDao(ReservationDao reservationDao) {
		this.reservationDao = reservationDao;
	}

	@Override
	public ReservationDTO selectOne(int productId) {
		if(productId <=0) {
			return null;
		}
		ReservationDTO dto = reservationDao.selectOne(productId);
		dto.setPriceList(reservationDao.selectPrice(productId));
		return dto;
	}

}
