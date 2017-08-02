package kr.or.reservation.domain;

import java.sql.Date;
import java.sql.Timestamp;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class Category {
	private long id;
	private String name;
	public Category() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	public Category(String name) {
		super();
		this.name = name;
	}


	public Category(long id, String name) {
		super();
		this.id = id;
		this.name = name;
	}


	public long getId() {
		return id;
	}


	public void setId(long id) {
		this.id = id;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE).toString();
	}
	
	

	
	
	
}
