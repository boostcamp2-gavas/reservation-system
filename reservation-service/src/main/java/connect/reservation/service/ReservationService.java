package connect.reservation.service;

import java.sql.Timestamp;
import java.util.List;

import connect.reservation.domain.ReservationInfo;
import connect.reservation.dto.Reservation;
import connect.reservation.dto.ReservationCount;

public interface ReservationService {
	public int add(ReservationInfo reservationInfo);
	public List<Reservation> get(int userId);
	public List<ReservationCount> getCount(int userId);
}
