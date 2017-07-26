package kr.or.reservation.service;

import java.util.List;
import java.util.Map;

import kr.or.reservation.dto.UserReservationDTO;

public interface UserReservationService {

	public List<UserReservationDTO> selectAll(int userId);
	public Map<String,Integer> getTypeCount(List<UserReservationDTO> list);
	public boolean cancelReservation(int userId,int reservationId);

	
}
