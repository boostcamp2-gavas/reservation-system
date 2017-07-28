package connect.reservation.domain;

import java.sql.Timestamp;

public class File {
	private int id;
	private int userId;
	private String fileName;
	private String saveFileName;
	private long fileLength;
	private String contentType;
	private int deleteFlag;
	private Timestamp createDate;
	private Timestamp modifyDate;
	
	
	public File() {
		
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public int getUserId() {
		return userId;
	}


	public void setUserId(int userId) {
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


	public String getContentType() {
		return contentType;
	}


	public void setContentType(String contentType) {
		this.contentType = contentType;
	}


	public int getDeleteFlag() {
		return deleteFlag;
	}


	public void setDeleteFlag(int deleteFlag) {
		this.deleteFlag = deleteFlag;
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
		return "File [id=" + id + ", userId=" + userId + ", fileName=" + fileName + ", saveFileName=" + saveFileName
				+ ", fileLength=" + fileLength + ", contentType=" + contentType + ", deleteFlag=" + deleteFlag
				+ ", createDate=" + createDate + ", modifyDate=" + modifyDate + "]";
	}
	
	
}
