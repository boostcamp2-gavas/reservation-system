package kr.or.reservation.dto;

public class ReservationTypeCountDTO {
	
	private int reservationType;
	private int count;
	
	public int getReservationType() {
		return reservationType;
	}
	
	public void setReservationType(int reservationType) {
		this.reservationType = reservationType;
	}
	
	public int getCount() {
		return count;
	}
	
	public void setCount(int count) {
		this.count = count;
	}

	@Override
	public String toString() {
		return "reservationType : \"" + reservationType + "\", count : \"" + count;
	}
	
	

}
