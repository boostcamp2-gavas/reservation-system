package kgw.reservation.dto;

public class Criteria {
	private Integer offset;
	private Integer size;
	public Integer getOffset() {
		return offset;
	}
	public void setOffset(Integer offset) {
		this.offset = offset;
	}
	public Integer getSize() {
		return size;
	}
	public void setSize(Integer size) {
		this.size = size;
	}
	@Override
	public String toString() {
		return "Criteria [offset=" + offset + ", size=" + size + "]";
	}
	
}
