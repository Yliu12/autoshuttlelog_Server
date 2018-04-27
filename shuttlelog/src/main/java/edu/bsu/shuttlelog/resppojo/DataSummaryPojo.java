package edu.bsu.shuttlelog.resppojo;

import java.math.BigDecimal;

public class DataSummaryPojo {

	private String stop;

	private BigDecimal totalNumBoarded;

	private BigDecimal totalNumLeft;

	private String loop;
	private int Hour;

	public String getLoop() {
		return loop;
	}

	public void setLoop(String loop) {
		this.loop = loop;
	}

	public int getHour() {
		return Hour;
	}

	public void setHour(int hour) {
		Hour = hour;
	}

	public String getStop() {
		return stop;
	}

	public void setStop(String stop) {
		this.stop = stop;
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
		return "DataSummaryByStop [stop=" + stop + ", totalNumBoarded=" + totalNumBoarded + ", totalNumLeft="
				+ totalNumLeft + "]";
	}



}
