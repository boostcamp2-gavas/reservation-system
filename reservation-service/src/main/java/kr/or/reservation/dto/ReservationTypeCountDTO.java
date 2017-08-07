package kr.or.reservation.dto;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

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
		return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE).toString();
	}
	
	

}
