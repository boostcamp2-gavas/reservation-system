package connect.reservation.domain;

import java.sql.Timestamp;

public class ProductDetail {
	private int id;
	private int productId;
	private String content;
	private Timestamp createDate;
	private Timestamp modifyDate;
	
	
	public ProductDetail() {
		
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


	public String getContent() {
		return content;
	}


	public void setContent(String content) {
		this.content = content;
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
		return "ProductDetail [id=" + id + ", productId=" + productId + ", content=" + content + ", createDate="
				+ createDate + ", modifyDate=" + modifyDate + "]";
	}
	
	
}
