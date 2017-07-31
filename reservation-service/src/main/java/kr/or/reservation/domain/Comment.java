package kr.or.reservation.domain;

import java.sql.Timestamp;

public class Comment {
	private String nickname;
	private String id; // comment id
	private String fileId;
	private String productId;
	private String userId;
	private String score;
	private String comment;
	private Timestamp createDate;
	private int imageCount;
	private String firstImageSaveFileName;

	public Comment() {
		// TODO Auto-generated constructor stub
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

	public String getFileId() {
		return fileId;
	}

	public void setFileId(String fileId) {
		this.fileId = fileId;
	}

	public int getImageCount() {
		return imageCount;
	}

	public void setImageCount(int imageCount) {
		this.imageCount = imageCount;
	}

	public String getFirstImageSaveFileName() {
		return firstImageSaveFileName;
	}

	public void setFirstImageSaveFileName(String firstImageSaveFileName) {
		this.firstImageSaveFileName = firstImageSaveFileName;
	}

	@Override
	public String toString() {
		return "nickname : \"" + nickname + "\", id : \"" + id + "\", fileId : \"" + fileId + "\", productId : \""
				+ productId + "\", userId : \"" + userId + "\", score : \"" + score + "\", comment : \"" + comment
				+ "\", createDate : \"" + createDate + "\", imageCount : \"" + imageCount
				+ "\", firstImageSaveFileName : \"" + firstImageSaveFileName;
	}

}
