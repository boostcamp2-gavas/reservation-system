package connect.reservation.domain;

import java.sql.Timestamp;

public class Product {
	private int id;
	private int categoryId;
	private String name;
	private String descriptrion;
	private Timestamp salesStart;
	private Timestamp salesEnd;
	private int salesFlag;
	private String event;
	private Timestamp createDate;
	private Timestamp modifyDate;
	
	
	public Product() {
		
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public int getCategoryId() {
		return categoryId;
	}


	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getDescriptrion() {
		return descriptrion;
	}


	public void setDescriptrion(String descriptrion) {
		this.descriptrion = descriptrion;
	}


	public Timestamp getSalesStart() {
		return salesStart;
	}


	public void setSalesStart(Timestamp salesStart) {
		this.salesStart = salesStart;
	}


	public Timestamp getSalesEnd() {
		return salesEnd;
	}


	public void setSalesEnd(Timestamp salesEnd) {
		this.salesEnd = salesEnd;
	}


	public int getSalesFlag() {
		return salesFlag;
	}


	public void setSalesFlag(int salesFlag) {
		this.salesFlag = salesFlag;
	}


	public String getEvent() {
		return event;
	}


	public void setEvent(String event) {
		this.event = event;
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
		return "Product [id=" + id + ", categoryId=" + categoryId + ", name=" + name + ", descriptrion=" + descriptrion
				+ ", salesStart=" + salesStart + ", salesEnd=" + salesEnd + ", salesFlag=" + salesFlag + ", event="
				+ event + ", createDate=" + createDate + ", modifyDate=" + modifyDate + "]";
	}
	
}
