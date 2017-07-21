package kr.or.reservation.domain;

import java.util.HashMap;
import java.util.Map;

public class AVGForComment {
	private Long amountOfCount;
	private Float avgScore;
	
	private static Map<Integer,Float> AVGMAP = new HashMap<>();
	private static Map<Integer,Long> COUNTMAP= new HashMap<>();
	
	
	public AVGForComment() {
		// TODO Auto-generated constructor stub
	}

	public AVGForComment(Long amountOfCount, Float avgScore) {
		this.amountOfCount = amountOfCount;
		this.avgScore = avgScore;
	}

	public Long getAmountOfCount() {
		return amountOfCount;
	}

	public void setAmountOfCount(Long amountOfCount) {
		this.amountOfCount = amountOfCount;
	}

	public Float getAvgScore() {
		return avgScore;
	}

	public void setAvgScore(Float avgScore) {
		this.avgScore = avgScore;
	}

	@Override
	public String toString() {
		return "amountOfCount : \"" + amountOfCount + "\", avgScore : \"" + avgScore +"\"";
	}
	
	public static Long getCount(int index) {
		return COUNTMAP.get(index); 
	}
	public static void setCount(int index , Long value) {
		COUNTMAP.put(index, value);
	}
	
	public static Float getAVG(int index) {
		return AVGMAP.get(index); 
	}
	public static void setAVG(int index , Float value) {
		AVGMAP.put(index, value);
	}
	
}
