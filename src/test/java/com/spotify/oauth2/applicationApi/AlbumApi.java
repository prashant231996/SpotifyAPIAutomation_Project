package com.spotify.oauth2.applicationApi;

import io.restassured.response.Response;
import static io.restassured.RestAssured.given;

import java.util.HashMap;

import com.spotify.oauth2.api.Routes;
import com.spotify.oauth2.api.SpecBuilder;
import com.spotify.oauth2.api.TokenManager;

public class AlbumApi {
	
	public static Response saveAlbum(HashMap payload)
	{
		return given()
				.spec(SpecBuilder.getRequestSpec())
				.auth().oauth2(TokenManager.getToken())
				.body(payload)
				.when()
				.put(Routes.saveAlbum)
				.then().spec(SpecBuilder.getResponseSpec())
				.extract().response();		
	}
	
	public static Response getAlubum()
	{
		return given().spec(SpecBuilder.getRequestSpec())
				.auth().oauth2(TokenManager.getToken())
				.when()
				.get(Routes.getAlbum)
				.then().spec(SpecBuilder.getResponseSpec())
				.extract().response();
	}
	
	public static Response removeAlbum(HashMap payload)
	{
		return given()
				.spec(SpecBuilder.getRequestSpec())
				.auth().oauth2(TokenManager.getToken())
				.body(payload)
				.when()
				.delete(Routes.removeAlbum)
				.then().spec(SpecBuilder.getResponseSpec())
				.extract().response();
	}
	
	public static Response usersSavedAlbum(String id)
	{
		return given()
				.spec(SpecBuilder.getRequestSpec())
				.auth().oauth2(TokenManager.getToken())
				.queryParam("ids", id)
				.when()
				.get(Routes.getCurrantUserPlaylist)
				.then().spec(SpecBuilder.getResponseSpec())
				.extract().response();
	}

}
