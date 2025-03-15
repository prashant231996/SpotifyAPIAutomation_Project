package com.spotify.oauth2.applicationApi;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.*;

import java.util.HashMap;
import java.util.List;

import com.spotify.oauth2.api.Routes;
import com.spotify.oauth2.api.SpecBuilder;
import com.spotify.oauth2.api.TokenManager;

public class PlayerApi {
	
	public static Response addItemToPlayBackQueue(String uriValue)
	{
		return given()
				.spec(SpecBuilder.getRequestSpec())
				.auth().oauth2(TokenManager.getToken())
				.queryParam("uri", uriValue)
				.when()
				.post(Routes.addItemToPlayBackQueue)
				.then()
				.spec(SpecBuilder.getResponseSpec())
				.extract().response();
	}
	
	public static Response getPlaybackState()
	{
		return given()
				.spec(SpecBuilder.getRequestSpec())
				.auth().oauth2(TokenManager.getToken())
				.when()
				.get(Routes.getPlayBackState)
				.then()
				.spec(SpecBuilder.getResponseSpec())
				.extract().response();
	}
	
	public static Response transferAndPlayBack(List<String>deviceIds)
	{
		HashMap<String,Object>deviceIdsMap=new HashMap<String,Object>();
		deviceIdsMap.put("device_ids", deviceIds);
		return given()
				.spec(SpecBuilder.getRequestSpec())
				.auth().oauth2(TokenManager.getToken())
				.body(deviceIdsMap)
				.when()
				.put(Routes.transferbackPlay)
				.then()
				.spec(SpecBuilder.getResponseSpec())
				.extract().response();
	}
	
	public static Response getAvailableDevices()
	{
		return given()
				.spec(SpecBuilder.getRequestSpec())
				.auth().oauth2(TokenManager.getToken())
				.when()
				.get(Routes.getAvailableDevices)
				.then()
				.spec(SpecBuilder.getResponseSpec())
				.extract().response();
	}

}
