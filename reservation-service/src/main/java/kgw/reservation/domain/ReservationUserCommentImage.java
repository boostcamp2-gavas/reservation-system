package kgw.reservation.domain;

public class ReservationUserCommentImage {
	private Integer id;
	private Integer reservationUserCommentId;
	private Integer fileId;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getReservationUserCommentId() {
		return reservationUserCommentId;
	}
	public void setReservationUserCommentId(Integer reservationUserCommentId) {
		this.reservationUserCommentId = reservationUserCommentId;
	}
	public Integer getFileId() {
		return fileId;
	}
	public void setFileId(Integer fileId) {
		this.fileId = fileId;
	}
	
}
