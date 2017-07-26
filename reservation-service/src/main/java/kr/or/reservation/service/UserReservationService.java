package kr.or.reservation.service;

import java.util.List;

import kr.or.reservation.dto.UserReservationDTO;

public interface UserReservationService {

	public List<UserReservationDTO> selectAll(int userId);
}
