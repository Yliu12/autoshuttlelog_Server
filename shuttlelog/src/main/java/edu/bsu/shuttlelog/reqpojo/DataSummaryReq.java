package edu.bsu.shuttlelog.reqpojo;

import java.sql.Date;

public class DataSummaryReq {

	private Date fromDate;
	private Date toDate;
	private String loopName;

	@Override
	public String toString() {
		return "DataSummaryReq [from=" + fromDate + ", to=" + toDate + ", loopName=" + loopName + "]";
	}

	public Date getFromDate() {
		return fromDate;
	}

	public void setFromDate(Date from) {
		this.fromDate = from;
	}

	public Date getToDate() {
		return toDate;
	}

	public void setToDate(Date to) {
		this.toDate = to;
	}

	public String getLoopName() {
		return loopName;
	}

	public void setLoopName(String loopName) {
		this.loopName = loopName;
	}
}
