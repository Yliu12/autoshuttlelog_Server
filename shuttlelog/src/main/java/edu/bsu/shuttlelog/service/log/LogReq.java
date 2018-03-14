package edu.bsu.shuttlelog.service.log;

import java.util.List;

import edu.bsu.shuttlelog.entity.Log;
import edu.bsu.shuttlelog.entity.User;

public class LogReq {
	private User user;
	private List<Log> logList;

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<Log> getLogList() {
		return logList;
	}

	public void setLogList(List<Log> logList) {
		this.logList = logList;
	}

	@Override
	public String toString() {
		return "LogReq [user=" + user + ", logList=" + logList + "]";
	}

}
