package kr.or.reservation.dto;

import java.sql.Timestamp;
import java.util.List;

public class CommentDTO {
	private String nickname;
	private String id; // comment id
	private String fileId;
	private String productId;
	private String userId;
	private String score;
	private String comment;
	private Timestamp createDate;
	private int count;
	
	public CommentDTO() {
		// TODO Auto-generated constructor stub
	}





	public CommentDTO(String nickname, String id, String fileId, String productId, String userId, String score,
			String comment, Timestamp createDate, int count) {
		super();
		this.nickname = nickname;
		this.id = id;
		this.fileId = fileId;
		this.productId = productId;
		this.userId = userId;
		this.score = score;
		this.comment = comment;
		this.createDate = createDate;
		this.count = count;
	}





	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getScore() {
		return score;
	}

	public void setScore(String score) {
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


	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}





	public String getFileId() {
		return fileId;
	}





	public void setFileId(String fileId) {
		this.fileId = fileId;
	}





	@Override
	public String toString() {
		return "nickname : \"" + nickname + "\", id : \"" + id + "\", fileId : \"" + fileId + "\", productId : \""
				+ productId + "\", userId : \"" + userId + "\", score : \"" + score + "\", comment : \"" + comment
				+ "\", createDate : \"" + createDate + "\", count : \"" + count;
	}



	
}
