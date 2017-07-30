package kr.or.reservation.domain;

import java.sql.Timestamp;

public class FileDomain {
	private long id; // imageId
	private long userId;
	private String fileName;
	private String saveFileName;
	private long fileLength;
	private int deleteFlag;
	private String contentType;
	private Timestamp createDate;
	private Timestamp modifyDate;

	public FileDomain() {
		// TODO Auto-generated constructor stub
	}

	public FileDomain(long userId, String fileName, String saveFileName, long fileLength, int deleteFlag,
			String contentType, Timestamp createDate, Timestamp modifyDate) {
		super();
		this.userId = userId;
		this.fileName = fileName;
		this.saveFileName = saveFileName;
		this.fileLength = fileLength;
		this.deleteFlag = deleteFlag;
		this.contentType = contentType;
		this.createDate = createDate;
		this.modifyDate = modifyDate;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

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

	public long getFileLength() {
		return fileLength;
	}

	public void setFileLength(long fileLength) {
		this.fileLength = fileLength;
	}

	public int getDeleteFlag() {
		return deleteFlag;
	}

	public void setDeleteFlag(int deleteFlag) {
		this.deleteFlag = deleteFlag;
	}

	public String getContentType() {
		return contentType;
	}

	public void setContentType(String contentType) {
		this.contentType = contentType;
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
		return "id : \"" + id + "\", userId : \"" + userId + "\", fileName : \"" + fileName + "\", saveFileName : \""
				+ saveFileName + "\", fileLength : \"" + fileLength + "\", deleteFlag : \"" + deleteFlag
				+ "\", contentType : \"" + contentType + "\", createDate : \"" + createDate + "\", modifyDate : \""
				+ modifyDate;
	}

}
