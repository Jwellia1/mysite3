package com.javaex.vo;

public class JsonResult {
	
	//필드
	private String result;  /*'success' or 'fail'*/
	private Object data;	//성공했을 때 result='success'
	private String failMsg; //실패했을 때 result='fail' 참고하는 메시지
	
	
	public JsonResult() {
		super();
	}

	//생성자
	public JsonResult(String result, Object data, String failMsg) {
		super();
		this.result = result;
		this.data = data;
		this.failMsg = failMsg;
	}
	//성공했을 때
	public void success(Object data) {
		this.result="success";
		this.data=data;
	}
	//실패 했을 떄
	public void fail(String msg) {
		this.result="fail";
		this.failMsg=msg;
		
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public String getFailMsg() {
		return failMsg;
	}

	public void setFailMsg(String failMsg) {
		this.failMsg = failMsg;
	}
	
	/*메서드*/
	@Override
	public String toString() {
		return "JsonResult [result=" + result + ", data=" + data + ", failMsg=" + failMsg + "]";
	}
	
	
	
	
	
}
