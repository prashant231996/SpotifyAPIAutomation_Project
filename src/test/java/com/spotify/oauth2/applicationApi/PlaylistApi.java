package com.spotify.oauth2.applicationApi;

import static io.restassured.RestAssured.given;

import java.time.Instant;

import com.spotify.oauth2.api.Routes;
import com.spotify.oauth2.api.SpecBuilder;
import com.spotify.oauth2.api.TokenManager;
import com.spotify.oauth2.pojo.Playlist;

import io.restassured.response.Response;

public class PlaylistApi {
	
	
	public static Response createPlaylist(Playlist reqPlaylist,String userId)
	{
		return given()
				.spec(SpecBuilder.getRequestSpec())
				.pathParam("userId", userId)
				.header("Authorization","Bearer "+TokenManager.getToken())
				.body(reqPlaylist)
				.when()
				.post(Routes.cratePlayList)
				.then().spec(SpecBuilder.getResponseSpec())
				.extract().response();
	}
	
	public static Response createPlaylist(Playlist reqPlaylist,String userId,String token)
	{
		return given()
				.spec(SpecBuilder.getRequestSpec())
				.pathParam("userId", userId)
				.header("Authorization","Bearer "+token)
				.body(reqPlaylist)
				.when()
				.post(Routes.cratePlayList)
				.then().spec(SpecBuilder.getResponseSpec())
				.extract().response();
	}
	
	public static Response getPlaylist(String playlistId)
	{
		return given().spec(SpecBuilder.getRequestSpec())
		.header("Authorization","Bearer "+TokenManager.getToken())		
		.pathParam("playlistId", playlistId)
		.when()
		.get(Routes.getPlayList)
		.then().spec(SpecBuilder.getResponseSpec())
		.extract().response();
	}
	
	public static Response updatePlaylist(String playlistId,Playlist payload)
	{
		return given().spec(SpecBuilder.getRequestSpec())
		.pathParam("playlistId", playlistId)
		.header("Authorization","Bearer "+TokenManager.getToken())
		.body(payload)
		.when()
		.put(Routes.updatePlaylist)
		.then().spec(SpecBuilder.getResponseSpec())
		.extract().response();
	}

}
