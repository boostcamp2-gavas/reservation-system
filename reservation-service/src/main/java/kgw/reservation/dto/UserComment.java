package kgw.reservation.dto;

import java.util.Date;
import java.util.List;

public class UserComment {
	private Integer id;
	private Integer userId;
	private String username;
	private Double score;
	private String comment;
	private Date createDate;
	private Date modifyDate;
	
	
	private String reservationDate;
	private List<FileImage> commentImageList;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public Double getScore() {
		return score;
	}
	public void setScore(Double score) {
		this.score = score;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
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
	public String getReservationDate() {
		return reservationDate;
	}
	public void setReservationDate(String reservationDate) {
		this.reservationDate = reservationDate;
	}
	public List<FileImage> getCommentImageList() {
		return commentImageList;
	}
	public void setCommentImageList(List<FileImage> commentImage) {
		this.commentImageList = commentImage;
	}
	@Override
	public String toString() {
		return "UserComment [id=" + id + ", userId=" + userId + ", username=" + username + ", score=" + score
				+ ", comment=" + comment + ", createDate=" + createDate + ", modifyDate=" + modifyDate
				+ ", reservationDate=" + reservationDate + ", commentImageList=" + commentImageList + "]";
	}
	
	
}
