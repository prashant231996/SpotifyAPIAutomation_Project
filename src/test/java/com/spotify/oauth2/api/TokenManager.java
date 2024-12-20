package com.spotify.oauth2.api;

import java.time.Instant;
import java.util.HashMap;

import org.testng.Assert;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;

public class TokenManager {
	
	private static String access_token;
	private static Instant expiryTime;
	
	public static String getToken()
	{
		try
		{
			if(access_token == null || Instant.now().isAfter(expiryTime))
			{
				System.out.println("RENEWING TOKEN...");
				Response res=renewToken();
				JsonPath js=new JsonPath(res.asString());
				access_token = js.get("access_token").toString();
				int expiryDurationInSeconds=js.get("expires_in");
				//Taking buffer of 5 minutes so we are excluding 300 seconds(5 MIN) form expiry time.
				expiryTime=Instant.now().plusSeconds(expiryDurationInSeconds - 300);
			}
			else
			{
				System.out.println("Token is good to use..");
			}
		}
		catch(Exception e)
		{
			throw new RuntimeException("!!!ABORT FAILED TO RENEW TOKEN!!!");
		}
		return access_token;
	}
	
	public static Response renewToken()
	{
		HashMap<String,String>formParam=new HashMap<String,String>();
		formParam.put("grant_type", "refresh_token");
		formParam.put("refresh_token", "AQBPofEoJufmhEHpoROyiTcDB17AeV0O9qQcvfTAgo8EV4vl2fcCNWgDuXwppdR48nBYVqzNDauDvgCjvMN77wyOJSIZs1iWp7d-kVfnT3JPJXHGjW_sxRcOq1odVSRb15s");
		formParam.put("client_id","aa7540425c6a42f4a6e1fd6bb2ddbb73");
		formParam.put("client_secret", "2f091187e55d43f2959ab7e7d48f892d");
		
		return given()
				.contentType(ContentType.URLENC)
				.formParams(formParam)
				.when()
				.post("https://accounts.spotify.com/api/token")
				.then().spec(SpecBuilder.getResponseSpec())
				.assertThat().statusCode(200)
				.extract().response();
	}

}
