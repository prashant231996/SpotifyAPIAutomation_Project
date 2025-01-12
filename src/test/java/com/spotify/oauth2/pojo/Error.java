package com.spotify.oauth2.pojo;

public class Error {
	
	private int statusCode;
	private String message;
	
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
	
	

}
