package edu.bsu.shuttlelog.resppojo;

public class RespException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String retCd; // 异常对应的返回码
	private String msgDes; // 异常对应的描述信息
	private Exception exception;

	public RespException() {
		super();
	}

	public RespException(String message) {
		super(message);
		msgDes = message;
	}

	public RespException(String retCd, String msgDes, Exception e) {
		super();
		this.retCd = retCd;
		this.msgDes = msgDes;
		this.exception = e;
	}

	public String getRetCd() {
		return retCd;
	}

	public void setRetCd(String retCd) {
		this.retCd = retCd;
	}

	public String getMsgDes() {
		return msgDes;
	}

	public void setMsgDes(String msgDes) {
		this.msgDes = msgDes;
	}

	public Exception getException() {
		return exception;
	}

	public void setException(Exception exception) {
		this.exception = exception;
	}



}
