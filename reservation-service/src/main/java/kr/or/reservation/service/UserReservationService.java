package kr.or.reservation.service;

import java.util.List;
import java.util.Map;

import kr.or.reservation.dto.UserReservationDTO;

public interface UserReservationService {

	public List<UserReservationDTO> selectReservationByType(int userId,int type);
	public boolean cancelReservation(int userId,int reservationId);

	
}
