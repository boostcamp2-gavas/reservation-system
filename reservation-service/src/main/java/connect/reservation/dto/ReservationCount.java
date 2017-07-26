package connect.reservation.dto;

// reservation_info 테이블에서 reservationType별로 개수를 담는다. 
public class ReservationCount {
	private int reservationType;
	private int cnt;
	
	public ReservationCount() {}

	public ReservationCount(int reservationType, int cnt) {
		this.reservationType = reservationType;
		this.cnt = cnt;
	}

	public int getReservationType() {
		return reservationType;
	}

	public void setReservationType(int reservationType) {
		this.reservationType = reservationType;
	}

	public int getCnt() {
		return cnt;
	}

	public void setCnt(int cnt) {
		this.cnt = cnt;
	}
	
}
