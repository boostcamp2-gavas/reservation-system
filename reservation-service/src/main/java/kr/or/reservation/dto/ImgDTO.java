package kr.or.reservation.dto;

<<<<<<< HEAD:reservation-service/src/main/java/kr/or/reservation/dto/ImgDTO.java
=======
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

>>>>>>> 0d95395487ea32084ee49af481f7933ef7c9a78a:reservation-service/src/main/java/kr/or/reservation/dto/ImgDTO.java
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
