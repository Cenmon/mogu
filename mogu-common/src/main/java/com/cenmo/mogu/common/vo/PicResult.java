package com.cenmo.mogu.common.vo;

public class PicResult {
	private int error;
	private String url;
	private String message;
	
	@Override
	public String toString() {
		return "PicResult [error=" + error + ", url=" + url + ", message=" + message + "]";
	}

	private PicResult(int error,String url,String message) {
		this.error = error;
		this.url = url;
		this.message = message;
	}
	
	public static PicResult ok(String url) {
		return new PicResult(0, url, null);
	}
	
	public static PicResult error(String message) {
		return new PicResult(1, null, message);
	}
	
	public int getError() {
		return error;
	}

	public void setError(int error) {
		this.error = error;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	
}
