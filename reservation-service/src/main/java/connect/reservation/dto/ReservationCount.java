package connect.reservation.dto;

import connect.reservation.domain.ReservationType;

// reservation_info 테이블에서 reservationType별로 개수를 담는다. 
public class ReservationCount {
	private ReservationType reservationType;
	private int cnt;
	
	public ReservationCount() {}

	public ReservationCount(ReservationType reservationType, int cnt) {
		this.reservationType = reservationType;
		this.cnt = cnt;
	}

	public ReservationType getReservationType() {
		return reservationType;
	}

	public void setReservationType(ReservationType reservationType) {
		this.reservationType = reservationType;
	}

	public int getCnt() {
		return cnt;
	}

	public void setCnt(int cnt) {
		this.cnt = cnt;
	}
	
}
