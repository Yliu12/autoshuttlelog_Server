package edu.bsu.shuttlelog.entity;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "sl_user")
// @Where(clause = "STATUS_CODE='1'")
// active user only

public class User {
	@Id
	//@GeneratedValue(strategy=GenerationType.AUTO)
	
	//Oracle

	@SequenceGenerator(name = "generator", allocationSize = 1, initialValue = 1, sequenceName = "	SL_USER_seq")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "generator")
	@Column(name = "id")
	private long id; 

	@Column(name = "Create_time")
	private Timestamp createTime;
	
	@Column(name = "last_update_time")
	private Timestamp lastUpdateTime;

	@Column(name = "First_NAME")
	private String firstName;

	@Column(name = "Last_NAME")
	private String LastName;

	@Column(name = "Password")
	private String password;

	@Column(name = "USERNAME", unique = true)
	private String userName;

	@Column(name = "ROLE")
	private String role;

	@Column(name = "STATUS_CODE")
	private String statusCode;
	

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}


	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return LastName;
	}

	public void setLastName(String lastName) {
		LastName = lastName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(String statusCode) {
		this.statusCode = statusCode;
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

	@Override
	public String toString() {
		return "User [id=" + id + ", createTime=" + createTime + ", lastUpdateTime=" + lastUpdateTime + ", firstName="
				+ firstName + ", LastName=" + LastName + ", password=" + password + ", userName=" + userName + ", role="
				+ role + ", statusCode=" + statusCode + "]";
	}

	

}
