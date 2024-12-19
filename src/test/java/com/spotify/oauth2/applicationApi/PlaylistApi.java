package com.spotify.oauth2.applicationApi;

import static io.restassured.RestAssured.given;

import com.spotify.oauth2.api.SpecBuilder;
import com.spotify.oauth2.pojo.Playlist;

import io.restassured.response.Response;

public class PlaylistApi {
	
	public static String access_token="BQADELb5yPKfUTfEZmKCk0ZND1vIWAq4D6OLoHaHDGxkfVn8jWly02Kh8aGfiQR7Rnu7cnrTJLCv6yG9URzHpYegYmum7QVenB-XR7CBWWJoxT3bylQxHXzVefsCr3yGkY6lH_QDrRoffbxg4d0jlquIDNClKkG3G4IB9PWk65O4sAscXxnUcK_0-oy8SI4l1kzY5eAiKcthrTjBmNPTYVpOUMQ3qlue-O2BPJ8Ov39X1ZwwNA3kgGlQWjODVGoyYNpZ-pvMpJMja1qYgQYcSKYC";
	
	public static Response createPlaylist(Playlist reqPlaylist,String userId)
	{
		return given()
				.spec(SpecBuilder.getRequestSpec())
				.pathParam("userId", userId)
				.header("Authorization","Bearer "+access_token)
				.body(reqPlaylist)
				.when()
				.post("/users/{userId}/playlists")
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
				.post("/users/{userId}/playlists")
				.then().spec(SpecBuilder.getResponseSpec())
				.extract().response();
	}
	
	public static Response getPlaylist(String playlistId)
	{
		return given().spec(SpecBuilder.getRequestSpec())
		.header("Authorization","Bearer "+access_token)		
		.pathParam("playlistId", playlistId)
		.when()
		.get("/playlists/{playlistId}")
		.then().spec(SpecBuilder.getResponseSpec())
		.extract().response();
	}
	
	public static Response updatePlaylist(String playlistId,Playlist payload)
	{
		return given().spec(SpecBuilder.getRequestSpec())
		.pathParam("playlistId", playlistId)
		.header("Authorization","Bearer "+access_token)
		.body(payload)
		.when()
		.put("/playlists/{playlistId}")
		.then().spec(SpecBuilder.getResponseSpec())
		.extract().response();
	}

}
