package kgw.reservation.domain;

import java.util.Date;

public class ReservationInfo {
	private Integer id;
	private Integer productId;
	private Integer userId;
	private Integer generalTicketCount;
	private Integer youthTicketCount;
	private Integer childTicketCount;
	private String reservationName;
	private String reservationTel;
	private String reservationEmail;
	private String reservationType;
	private Date reservationDate;
	private Date createDate;
	private Date modifyDate;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getProductId() {
		return productId;
	}
	public void setProductId(Integer productId) {
		this.productId = productId;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public Integer getGeneralTicketCount() {
		return generalTicketCount;
	}
	public void setGeneralTicketCount(Integer generalTicketCount) {
		this.generalTicketCount = generalTicketCount;
	}
	public Integer getYouthTicketCount() {
		return youthTicketCount;
	}
	public void setYouthTicketCount(Integer youthTicketCount) {
		this.youthTicketCount = youthTicketCount;
	}
	public Integer getChildTicketCount() {
		return childTicketCount;
	}
	public void setChildTicketCount(Integer childTicketCount) {
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
	public String getReservationType() {
		return reservationType;
	}
	public void setReservationType(String reservationType) {
		this.reservationType = reservationType;
	}
	public Date getReservationDate() {
		return reservationDate;
	}
	public void setReservationDate(Date reservationDate) {
		this.reservationDate = reservationDate;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public Date getModifyDate() {
		return modifyDate;
	}
	public void setModifyDate(Date modifyDate) {
		this.modifyDate = modifyDate;
	}
	@Override
	public String toString() {
		return "ReservationInfo [id=" + id + ", productId=" + productId + ", userId=" + userId + ", generalTicketCount="
				+ generalTicketCount + ", youthTicketCount=" + youthTicketCount + ", childTicketCount="
				+ childTicketCount + ", reservationName=" + reservationName + ", reservationTel=" + reservationTel
				+ ", reservationEmail=" + reservationEmail + ", reservationType=" + reservationType
				+ ", reservationDate=" + reservationDate + ", createDate=" + createDate + ", modifyDate=" + modifyDate
				+ "]";
	}
	
	
}
