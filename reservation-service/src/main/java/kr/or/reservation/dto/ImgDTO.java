package kr.or.reservation.dto;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class ImgDTO {
	private int productId;
	private int fileId;
	private String saveFileName;
	private String type;

	public ImgDTO() {
		// TODO Auto-generated constructor stub
	}

	public ImgDTO(int productId, int fileId, String saveFileName, String type) {
		super();
		this.productId = productId;
		this.fileId = fileId;
		this.saveFileName = saveFileName;
		this.type = type;
	}

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public int getFileId() {
		return fileId;
	}

	public void setFileId(int fileId) {
		this.fileId = fileId;
	}

	public String getSaveFileName() {
		return saveFileName;
	}

	public void setSaveFileName(String saveFileName) {
		this.saveFileName = saveFileName;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE).toString();
	}
	

}
