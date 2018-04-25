package edu.bsu.shuttlelog.entity;

import java.math.BigInteger;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author yliu12
 *
 */
@Entity
@Table(name = "DAILYREPORT_STOP")
public class DailyReportByStop {
	@Id
	@Column(name = "id")
	private BigInteger id;

	@Column(name = "Record_date")
	private Date RecordDate;

	@Column(name = "LOOP_NAME")
	private String LoopName;

	@Column(name = "STOP_NAME")
	private String StopNmae;

	@Column(name = "NUMBER_BOARDED")
	private int numBoarded;

	@Column(name = "NUMBER_LEFT")
	private int numLeft;

	public BigInteger getId() {
		return id;
	}

	public void setId(BigInteger id) {
		this.id = id;
	}

	public Date getRecordDate() {
		return RecordDate;
	}

	public void setRecordDate(Date recordDate) {
		RecordDate = recordDate;
	}

	public String getLoopName() {
		return LoopName;
	}

	public void setLoopName(String loopName) {
		LoopName = loopName;
	}

	public String getStopNmae() {
		return StopNmae;
	}

	public void setStopNmae(String stopNmae) {
		StopNmae = stopNmae;
	}

	public int getNoBoarded() {
		return numBoarded;
	}

	public void setNoBoarded(int noBoarded) {
		numBoarded = noBoarded;
	}

	public int getNoLeft() {
		return numLeft;
	}

	public void setNoLeft(int noLeft) {
		numLeft = noLeft;
	}

	@Override
	public String toString() {
		return "DailyReportByStop [id=" + id + ", RecordDate=" + RecordDate + ", LoopName=" + LoopName + ", StopNmae="
				+ StopNmae + ", NoBoarded=" + numBoarded + ", NoLeft=" + numLeft + "]";
	}

}
