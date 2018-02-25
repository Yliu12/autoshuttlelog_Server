package edu.bsu.shuttlelog.resp;

public class MyResp {

	private Object respBody;
	private RespException error;

	public MyResp(Object respBody, RespException error) {
		this.respBody = respBody;
		this.error = error;
	}

	public MyResp(Object respBody) {
		this.respBody = respBody;
	}

	public MyResp(RespException error) {

		this.error = error;
	}

	public MyResp() {
	}

	public Object getRespBody() {
		return respBody;
	}

	public void setRespBody(Object respBody) {
		this.respBody = respBody;
	}

	public RespException getError() {
		return error;
	}

	public void setError(RespException error) {
		this.error = error;
	}

}
