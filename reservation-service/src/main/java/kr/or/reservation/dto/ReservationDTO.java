package kr.or.reservation.dto;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

public class ReservationDTO {
	private static SimpleDateFormat mmddFormat = new SimpleDateFormat("YYYY년 MM월 dd일");
	
	private int id;
	private String name;
	private Timestamp salesStart;
	private Timestamp salesEnd;
	private String observationTime;
	private String placeLot;
	private int fileId;
	private List<Map<String,Object>> priceList;
	
	public ReservationDTO() {
		// TODO Auto-generated constructor stub
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
	public String getSalesStart() {
		return mmddFormat.format(salesStart);
	}
	public void setSalesStart(Timestamp salesStart) {
		this.salesStart = salesStart;
	}
	public String getSalesEnd() {
		return mmddFormat.format(salesEnd);
	}
	public void setSalesEnd(Timestamp salesEnd) {
		this.salesEnd = salesEnd;
	}
	public String getObservationTime() {
		return observationTime;
	}
	public void setObservationTime(String observationTime) {
		this.observationTime = observationTime;
	}
	public String getPlaceLot() {
		return placeLot;
	}
	public void setPlaceLot(String placeLot) {
		this.placeLot = placeLot;
	}
	public List<Map<String,Object>> getPriceList() {
		return priceList;
	}
	public void setPriceList(List<Map<String,Object>> priceList) {
		this.priceList = priceList;
	}

	public static SimpleDateFormat getMmddFormat() {
		return mmddFormat;
	}

	public static void setMmddFormat(SimpleDateFormat mmddFormat) {
		ReservationDTO.mmddFormat = mmddFormat;
	}

	public int getFileId() {
		return fileId;
	}

	public void setFileId(int fileId) {
		this.fileId = fileId;
	}

	@Override
	public String toString() {
		return "id : \"" + id + "\", name : \"" + name + "\", salesStart : \"" + salesStart + "\", salesEnd : \""
				+ salesEnd + "\", observationTime : \"" + observationTime + "\", placeLot : \"" + placeLot
				+ "\", fileId : \"" + fileId + "\", priceList : \"" + priceList;
	}


	
	
	
	
	
}
