package connect.reservation.dto;

import java.sql.Timestamp;

import connect.reservation.domain.ReservationType;

public class Reservation {
	private int id;
	private int generalTicketCount;
	private int youthTicketCount;
	private int childTicketCount;
	private ReservationType reservationType;
	private int productId;
	private String productName;
	private Timestamp displayStart;
	private Timestamp displayEnd;
	private String placeName;
	private Double totalPrice;
	
	public Reservation() {}

	public Reservation(int id, int generalTicketCount, int youthTicketCount, int childTicketCount, ReservationType reservationType,
			int productId, String productName, Timestamp displayStart, Timestamp displayEnd, String placeName, Double totalPrice) {
		this.id = id;
		this.generalTicketCount = generalTicketCount;
		this.youthTicketCount = youthTicketCount;
		this.childTicketCount = childTicketCount;
		this.reservationType = reservationType;
		this.productId = productId;
		this.productName = productName;
		this.displayStart = displayStart;
		this.displayEnd = displayEnd;
		this.placeName = placeName;
		this.totalPrice = totalPrice;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getGeneralTicketCount() {
		return generalTicketCount;
	}

	public void setGeneralTicketCount(int generalTicketCount) {
		this.generalTicketCount = generalTicketCount;
	}

	public int getYouthTicketCount() {
		return youthTicketCount;
	}

	public void setYouthTicketCount(int youthTicketCount) {
		this.youthTicketCount = youthTicketCount;
	}

	public int getChildTicketCount() {
		return childTicketCount;
	}

	public void setChildTicketCount(int childTicketCount) {
		this.childTicketCount = childTicketCount;
	}

	public ReservationType getReservationType() {
		return reservationType;
	}

	public void setReservationType(ReservationType reservationType) {
		this.reservationType = reservationType;
	}

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public Timestamp getDisplayStart() {
		return displayStart;
	}

	public void setDisplayStart(Timestamp displayStart) {
		this.displayStart = displayStart;
	}

	public Timestamp getDisplayEnd() {
		return displayEnd;
	}

	public void setDisplayEnd(Timestamp displayEnd) {
		this.displayEnd = displayEnd;
	}

	public String getPlaceName() {
		return placeName;
	}

	public void setPlaceName(String placeName) {
		this.placeName = placeName;
	}

	public Double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(Double totalPrice) {
		this.totalPrice = totalPrice;
	}

}
