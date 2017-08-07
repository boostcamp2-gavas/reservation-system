package connect.reservation.dto;

import connect.reservation.domain.File;

public class UploadFile {
	private int userId;
	private int commentId;
	private String contentType;
	private String path;
	
//	private File file;

	
	public String getContentType() {
		return contentType;
	}
	
	public void setContentType(String contentType) {
		this.contentType = contentType;
	}
	
	public int getUsreId() {
		return userId;
	}
	
	public void setUserId(int userId) {
		this.userId = userId;
	}
	
	public int getCommentId() {
		return commentId;
	}

	public void setCommentId(int commentId) {
		this.commentId = commentId;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

/*	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}

	@Override
	public String toString() {
		return "UploadFile [commentId=" + commentId + ", path=" + path + ", file=" + file + "]";
	}*/
	@Override
	public String toString() {
		return "UploadFile [commentId=" + commentId + ", path=" + path + "]";
	}
	
}
