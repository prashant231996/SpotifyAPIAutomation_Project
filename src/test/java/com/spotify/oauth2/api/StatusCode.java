package com.spotify.oauth2.api;

public enum StatusCode {
	
	CODE_200(200,"",""),
	CODE_201(201,"",""),
	CODE_400(400,"Missing required field: name",""),
	CODE_404_ResourceNF(404,"Resource not found",""),
	CODE_404(404,"Not Found",""),
	CODE_401(401,"Invalid access token",""),
	CODE_401_withoutToken(401,"No token provided",""),
	CODE_403(403,"Player command failed: Premium required","PREMIUM_REQUIRED"),
	CODE_204(204,"","");

	public final int code;
	public final String message;
	public final String reason;
	
	StatusCode(int code, String message, String reason) {
		this.code=code;
		this.message=message;
		this.reason=reason;
	}
	
	/*public int getCode()
	{
		return code;
	}
	
	public String getMessage()
	{
		return message;
	}*/

}
