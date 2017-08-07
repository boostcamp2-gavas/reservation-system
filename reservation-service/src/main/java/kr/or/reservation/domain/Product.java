package kr.or.reservation.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class Product {
	private int id;
	private int categoryId;
	private String name;
	private String content;
	private String placeName;
	private String fileId;



	public Product() {
		// TODO Auto-generated constructor stub
	}

	public Product(int id, int categoryId, String name, String content, String placeName,String fileId) {
		super();
		this.id = id;
		this.categoryId = categoryId;
		this.name = name;
		this.content = content;
		this.placeName = placeName;
		this.fileId = fileId;
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

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getPlaceName() {
		return placeName;
	}

	public void setPlaceName(String placeName) {
		this.placeName = placeName;
	}

	public String getfileId() {
		return fileId;
	}

	public void setfileId(String fileId) {
		this.fileId = fileId;
	}
	
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE).toString();
	}

}
