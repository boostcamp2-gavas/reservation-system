package kr.or.reservation.dto;

import java.sql.Date;
import java.sql.Timestamp;

public class ProductDetailDTO {
	private int id;
	private String name;
	private String description;
	private String event;
	private String content;
	private String homepage;
	private String email;
	private String placeName;
	private String tel;
	private String placeLot;
	private String placeStreet;
	private String salesFlag;
	private Timestamp salesEnd;

	public ProductDetailDTO() {
		// TODO Auto-generated constructor stub
	}

	

	public ProductDetailDTO(int id, String name, String description, String event, String content, String homepage,
			String email, String placeName, String tel, String placeLot, String placeStreet, String salesFlag,
			Timestamp salesEnd) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.event = event;
		this.content = content;
		this.homepage = homepage;
		this.email = email;
		this.placeName = placeName;
		this.tel = tel;
		this.placeLot = placeLot;
		this.placeStreet = placeStreet;
		this.salesFlag = salesFlag;
		this.salesEnd = salesEnd;
	}



	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEvent() {
		return event;
	}

	public void setEvent(String event) {
		this.event = event;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getHomepage() {
		return homepage;
	}

	public void setHomepage(String homepage) {
		this.homepage = homepage;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPlaceName() {
		return placeName;
	}

	public void setPlaceName(String placeName) {
		this.placeName = placeName;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	
	
	public String getSalesFlag() {
		return salesFlag;
	}

	public void setSalesFlag(String salesFlag) {
		this.salesFlag = salesFlag;
	
	}
	
	public Timestamp getSalesEnd() {
		return salesEnd;
	}

	public void setSalesEnd(Timestamp salesEnd) {
		this.salesEnd = salesEnd;
	}



	public String getPlaceLot() {
		return placeLot;
	}



	public void setPlaceLot(String placeLot) {
		this.placeLot = placeLot;
	}



	public String getPlaceStreet() {
		return placeStreet;
	}



	public void setPlaceStreet(String placeStreet) {
		this.placeStreet = placeStreet;
	}



	@Override
	public String toString() {
		return "id : \"" + id + "\", name : \"" + name + "\", description : \"" + description + "\", event : \"" + event
				+ "\", content : \"" + content + "\", homepage : \"" + homepage + "\", email : \"" + email
				+ "\", placeName : \"" + placeName + "\", tel : \"" + tel + "\", placeLot : \"" + placeLot
				+ "\", placeStreet : \"" + placeStreet + "\", salesFlag : \"" + salesFlag + "\", salesEnd : \""
				+ salesEnd;
	}





	
}
