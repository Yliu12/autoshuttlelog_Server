package edu.bsu.shuttlelog.entity;

import java.math.BigInteger;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "log")
public class Log {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private BigInteger id;

	@Column(name = "LOOP_NAME")
	private String loopName;

	@Column(name = "STOP_SHORT")
	private String stop;

	@Column(name = "DRIVER_USERNAME")
	private String driver;

	@Column(name = "NUMBER_BOARDED")
	private String numberBoarded;

	@Column(name = "NUMBER_LEFT")
	private String numberLeft;

	@Column(name = "BUS_ID")
	private String busId;

	@Column(name = "RECORD_time")
	private Timestamp time;

	public Timestamp getTime() {
		return time;
	}

	public void setTime(Timestamp time) {
		this.time = time;
	}

	public BigInteger getId() {
		return id;
	}

	@Override
	public String toString() {
		return "Log [id=" + id + ", loopName=" + loopName + ", stop=" + stop + ", driver=" + driver + ", numberBoarded="
				+ numberBoarded + ", numberLeft=" + numberLeft + ", busId=" + busId + ", time=" + time + "]";
	}

	public void setId(BigInteger id) {
		this.id = id;
	}

	public String getLoopName() {
		return loopName;
	}

	public void setLoopName(String loopName) {
		this.loopName = loopName;
	}

	public String getStop() {
		return stop;
	}

	public void setStop(String stop) {
		this.stop = stop;
	}

	public String getDriver() {
		return driver;
	}

	public void setDriver(String driver) {
		this.driver = driver;
	}

	public String getNumberBoarded() {
		return numberBoarded;
	}

	public void setNumberBoarded(String numberBoarded) {
		this.numberBoarded = numberBoarded;
	}

	public String getNumberLeft() {
		return numberLeft;
	}

	public void setNumberLeft(String numberLeft) {
		this.numberLeft = numberLeft;
	}

	public String getBusId() {
		return busId;
	}

	public void setBusId(String busId) {
		this.busId = busId;
	}

}
