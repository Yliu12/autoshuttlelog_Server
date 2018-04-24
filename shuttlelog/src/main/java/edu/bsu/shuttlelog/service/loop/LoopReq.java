package edu.bsu.shuttlelog.service.loop;

import edu.bsu.shuttlelog.entity.Loop;

public class LoopReq {
	private Loop loop;
	private String loopName;
	public Loop getLoop() {
		return loop;
	}
	public void setLoop(Loop loop) {
		this.loop = loop;
	}
	public String getLoopName() {
		return loopName;
	}
	public void setLoopName(String loopName) {
		this.loopName = loopName;
	}
	@Override
	public String toString() {
		return "LoopReq [loop=" + loop + ", loopName=" + loopName + "]";
	}
	
	
}
