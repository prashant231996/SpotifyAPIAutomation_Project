package com.spotify.oauth2.pojo;

public class Error {
	
	private int statusCode;
	private String message;
	private String reason;
	
	public int getStatus() {
		return statusCode;
	}
	public void setStatus(int statusCode) {
		this.statusCode = statusCode;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
	
}
