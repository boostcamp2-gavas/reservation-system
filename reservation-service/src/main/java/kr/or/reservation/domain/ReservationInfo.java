package kr.or.reservation.domain;

import java.sql.Timestamp;

public class ReservationInfo {
	private int productId;
	private int userId;
	private int generalTicketCount;
	private int youthTicketCount;
	private int childTicketCount;
	private String reservationName;
	private String reservationTel;
	private String reservationEmail;
	private Timestamp reservationDate;
	private int reservationType;
	private Timestamp createDate;
	private Timestamp modifyDate;



	public ReservationInfo() {
		// TODO Auto-generated constructor stub
	}
	
	

	public ReservationInfo(int productId, int userId, int generalTicketCount, int youthTicketCount,
			int childTicketCount, String reservationName, String reservationTel, String reservationEmail,
			Timestamp reservationDate, int reservationType, Timestamp createDate, Timestamp modifyDate) {
		super();
		this.productId = productId;
		this.userId = userId;
		this.generalTicketCount = generalTicketCount;
		this.youthTicketCount = youthTicketCount;
		this.childTicketCount = childTicketCount;
		this.reservationName = reservationName;
		this.reservationTel = reservationTel;
		this.reservationEmail = reservationEmail;
		this.reservationDate = reservationDate;
		this.reservationType = reservationType;
		this.createDate = createDate;
		this.modifyDate = modifyDate;
	}



	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
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

	public String getReservationName() {
		return reservationName;
	}

	public void setReservationName(String reservationName) {
		this.reservationName = reservationName;
	}

	public String getReservationTel() {
		return reservationTel;
	}

	public void setReservationTel(String reservationTel) {
		this.reservationTel = reservationTel;
	}

	public String getReservationEmail() {
		return reservationEmail;
	}

	public void setReservationEmail(String reservationEmail) {
		this.reservationEmail = reservationEmail;
	}

	public Timestamp getReservationDate() {
		return reservationDate;
	}

	public void setReservationDate(Timestamp reservationDate) {
		this.reservationDate = reservationDate;
	}

	public int getReservationType() {
		return reservationType;
	}

	public void setReservationType(int reservationType) {
		this.reservationType = reservationType;
	}

	public Timestamp getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Timestamp createDate) {
		this.createDate = createDate;
	}

	public Timestamp getModifyDate() {
		return modifyDate;
	}

	public void setModifyDate(Timestamp modifyDate) {
		this.modifyDate = modifyDate;
	}

	@Override
	public String toString() {
		return "productId : \"" + productId + "\", userId : \"" + userId + "\", generalTicketCount : \""
				+ generalTicketCount + "\", youthTicketCount : \"" + youthTicketCount + "\", childTicketCount : \""
				+ childTicketCount + "\", reservationName : \"" + reservationName + "\", reservationTel : \""
				+ reservationTel + "\", reservationEmail : \"" + reservationEmail + "\", reservationDate : \""
				+ reservationDate + "\", reservationType : \"" + reservationType + "\", createDate : \"" + createDate
				+ "\", modifyDate : \"" + modifyDate;
	}

}
