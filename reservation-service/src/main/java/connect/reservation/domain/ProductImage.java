package connect.reservation.domain;

public class ProductImage {
	private int id;
	private int productId;
	private int fileId;
	private int type;
	
	
	public ProductImage() {
		
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
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


	public int getType() {
		return type;
	}


	public void setType(int type) {
		this.type = type;
	}


	@Override
	public String toString() {
		return "ProductImage [id=" + id + ", productId=" + productId + ", fileId=" + fileId + ", type=" + type + "]";
	}
	
	
}
