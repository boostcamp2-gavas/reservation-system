package connect.reservation.domain;

public enum ReservationType {
	REQUESTING("예약신청중"),DUE("이용예정"),USED("이용완료"),REFUND_CANCEL("환불/취소");
	
	private final String name;
	
	ReservationType(String name) {
		this.name = name;
	}
	public String toString() {
		return this.name;
	}
}
