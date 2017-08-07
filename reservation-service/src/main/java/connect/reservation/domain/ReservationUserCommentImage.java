package connect.reservation.domain;

public class ReservationUserCommentImage {
	private int reservation_user_comment_id;
	private int file_id;
	
	
	public int getReservation_user_comment_id() {
		return reservation_user_comment_id;
	}
	public void setReservation_user_comment_id(int reservation_user_comment_id) {
		this.reservation_user_comment_id = reservation_user_comment_id;
	}
	public int getFile_id() {
		return file_id;
	}
	public void setFile_id(int file_id) {
		this.file_id = file_id;
	}
	
	
	@Override
	public String toString() {
		return "ReservationUserCommentImage [reservation_user_comment_id=" + reservation_user_comment_id + ", file_id="
				+ file_id + "]";
	}
}
