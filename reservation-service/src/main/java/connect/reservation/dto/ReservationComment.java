package connect.reservation.dto;

<<<<<<< HEAD
=======
import java.sql.Timestamp;
>>>>>>> 675e75dfc3b5ee0e722079d046479cafa81aa8d7

public class ReservationComment {
	private int productId;
	private int categoryId;
	private int userId;
<<<<<<< HEAD
	private String nickname;
	private String reservationDate;
	private String reservationName;
	private double score;
	private String comment;
	private String fileName;
	private String saveFileName;
	private String createDate;
	private String modifyDate;
=======
	private String productName;
	private String nickname;
	private Timestamp reservationDate;
	private String reservationName;
	private double score;
	private String comment;
	private Integer fileId;
	private String fileName;
	private String saveFileName;
	private Timestamp createDate;
	private Timestamp modifyDate;
>>>>>>> 675e75dfc3b5ee0e722079d046479cafa81aa8d7
	private String contentType;
	private int imgCount;
	private int rucId;
	
	
	public int getRucId() {
		return rucId;
	}
	public void setRucId(int rucId) {
		this.rucId = rucId;
	}
	public int getProductId() {
		return productId;
	}
	public void setProductId(int productId) {
		this.productId = productId;
	}
	public int getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
<<<<<<< HEAD
=======
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
>>>>>>> 675e75dfc3b5ee0e722079d046479cafa81aa8d7
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String ninkname) {
		this.nickname = ninkname;
	}
<<<<<<< HEAD
	public String getReservationDate() {
		return reservationDate;
	}
	public void setReservationDate(String reservationDate) {
=======
	public Timestamp getReservationDate() {
		return reservationDate;
	}
	public void setReservationDate(Timestamp reservationDate) {
>>>>>>> 675e75dfc3b5ee0e722079d046479cafa81aa8d7
		this.reservationDate = reservationDate;
	}
	public String getReservationName() {
		return reservationName;
	}
	public void setReservationName(String reservationName) {
		this.reservationName = reservationName;
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
<<<<<<< HEAD
=======
	public Integer getFileId() {
		return fileId;
	}
	public void setFileId(Integer fileId) {
		this.fileId = fileId;
	}
>>>>>>> 675e75dfc3b5ee0e722079d046479cafa81aa8d7
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public String getSaveFileName() {
		return saveFileName;
	}
	public void setSaveFileName(String saveFileName) {
		this.saveFileName = saveFileName;
	}
<<<<<<< HEAD
	public String getCreateDate() {
		return createDate;
	}
	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}
	public String getModifyDate() {
		return modifyDate;
	}
	public void setModifyDate(String modifyDate) {
=======
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
>>>>>>> 675e75dfc3b5ee0e722079d046479cafa81aa8d7
		this.modifyDate = modifyDate;
	}
	public String getContentType() {
		return contentType;
	}
	public void setContentType(String contentType) {
		this.contentType = contentType;
	}
	public int getImgCount() {
		return imgCount;
	}
	public void setImgCount(int imgCount) {
		this.imgCount = imgCount;
	}
	
	
	@Override
	public String toString() {
		return "ReservationComment [productId=" + productId + ", categoryId=" + categoryId + ", userId=" + userId
				+ ", nickname=" + nickname + ", reservationDate=" + reservationDate + ", reservationName="
				+ reservationName + ", score=" + score + ", comment=" + comment + ", fileName=" + fileName
				+ ", saveFileName=" + saveFileName + ", createDate=" + createDate + ", modifyDate=" + modifyDate
				+ ", contentType=" + contentType + ", imgCount=" + imgCount + "]";
	}
}
