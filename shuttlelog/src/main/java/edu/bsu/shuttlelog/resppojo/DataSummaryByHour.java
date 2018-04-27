package edu.bsu.shuttlelog.resppojo;

import java.math.BigDecimal;

public class DataSummaryByHour {

	private String hour;

	private BigDecimal totalNumBoarded;

	private BigDecimal totalNumLeft;

	public String getHour() {
		return hour;
	}

	public void setHour(String hour) {
		this.hour = hour;
	}

	public BigDecimal getTotalNumBoarded() {
		return totalNumBoarded;
	}

	public void setTotalNumBoarded(BigDecimal totalNumBoarded) {
		this.totalNumBoarded = totalNumBoarded;
	}

	public BigDecimal getTotalNumLeft() {
		return totalNumLeft;
	}

	public void setTotalNumLeft(BigDecimal totalNumLeft) {
		this.totalNumLeft = totalNumLeft;
	}

	@Override
	public String toString() {
		return "DataSummaryByHour [hour=" + hour + ", totalNumBoarded=" + totalNumBoarded + ", totalNumLeft="
				+ totalNumLeft + "]";
	}

	

}
