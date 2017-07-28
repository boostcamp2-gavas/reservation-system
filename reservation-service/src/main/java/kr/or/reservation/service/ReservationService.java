package kr.or.reservation.service;

import org.springframework.stereotype.Service;

import kr.or.reservation.dto.ReservationDTO;

public interface ReservationService {
	
	public ReservationDTO selectOne(int productId);

}
