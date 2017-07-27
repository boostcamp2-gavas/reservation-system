package connect.reservation.domain;

import java.sql.Timestamp;


public class ReservationUserComment {
	private Integer id;
	private Integer productId;
	private Integer userId;
	private double score;
	private String comment;
	private Timestamp createDate;
	private Timestamp modifyDate;

	
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
	public double getScore() {
		return score;
	}
	public void setScore(double score) {
		this.score = score;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
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
		return "ReservationUserComment [id=" + id + ", productId=" + productId + ", userId=" + userId + ", score="
				+ score + ", comment=" + comment + ", createDate=" + createDate + ", modifyDate=" + modifyDate + "]";
	}
}
