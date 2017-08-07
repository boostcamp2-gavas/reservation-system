package kgw.reservation.dto;

public class MyReservationCount {
	private Integer total;
	private Integer schedule;
	private Integer completion;
	private Integer cancellationAndRefund;
	public MyReservationCount() {
		this.total = 0;
		this.schedule = 0;
		this.completion = 0;
		this.cancellationAndRefund = 0;
	}
	
	public Integer getTotal() {
		return total;
	}
	public void setTotal(Integer total) {
		this.total = total;
	}
	public Integer getSchedule() {
		return schedule;
	}
	public void setSchedule(Integer schedule) {
		this.schedule = schedule;
	}
	public Integer getCompletion() {
		return completion;
	}
	public void setCompletion(Integer completion) {
		this.completion = completion;
	}
	public Integer getCancellationAndRefund() {
		return cancellationAndRefund;
	}
	public void setCancellationAndRefund(Integer cancellationAndRefund) {
		this.cancellationAndRefund = cancellationAndRefund;
	}

	@Override
	public String toString() {
		return "MyReservationCount [total=" + total + ", schedule=" + schedule + ", completion=" + completion
				+ ", cancellationAndRefund=" + cancellationAndRefund + "]";
	}
	
	
}
