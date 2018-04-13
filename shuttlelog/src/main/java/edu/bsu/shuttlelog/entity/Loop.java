package edu.bsu.shuttlelog.entity;

import java.math.BigInteger;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "BSULoop")
public class Loop {

	@Id
	//@GeneratedValue(strategy = GenerationType.IDENTITY)

	@SequenceGenerator(name = "generator", allocationSize = 1, initialValue = 1, sequenceName = "BSULOOP_SEQ")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "generator")
	
	@Column(name = "id")
	private BigInteger id;

	@Column(name = "LOOP_NAME")
	private String loopName;

	@Column(name = "STOPS")
	private String stops;

	@Column(name = "Create_time")
	private Timestamp createTime;

	@Column(name = "last_update_time")
	private Timestamp lastUpdateTime;

	@Column(name = "STATUS_CODE")
	private String statusCode;

	public BigInteger getId() {
		return id;
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

	public String getStops() {
		return stops;
	}

	public void setStops(String stops) {
		this.stops = stops;
	}

	public Timestamp getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}

	public Timestamp getLastUpdateTime() {
		return lastUpdateTime;
	}

	public void setLastUpdateTime(Timestamp lastUpdateTime) {
		this.lastUpdateTime = lastUpdateTime;
	}

	public String getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(String statusCode) {
		this.statusCode = statusCode;
	}

	@Override
	public String toString() {
		return "Loop [id=" + id + ", loopName=" + loopName + ", stops=" + stops + ", createTime=" + createTime
				+ ", lastUpdateTime=" + lastUpdateTime + ", statusCode=" + statusCode + "]";
	}

}
