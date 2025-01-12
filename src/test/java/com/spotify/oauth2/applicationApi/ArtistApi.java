package com.spotify.oauth2.applicationApi;

import io.restassured.response.Response;
import static io.restassured.RestAssured.given;

import com.fasterxml.jackson.databind.util.TokenBuffer;
import com.spotify.oauth2.api.Routes;
import com.spotify.oauth2.api.SpecBuilder;
import com.spotify.oauth2.api.TokenManager;

public class ArtistApi {
	
	public static Response getArtistDetails(String artistId)
	{
		return given()
				.spec(SpecBuilder.getRequestSpec())
				.auth().oauth2(TokenManager.getToken())
				.pathParam("artistId", artistId)
				.when()
				.get(Routes.getArtistDetails)
				.then().spec(SpecBuilder.getResponseSpec())
				.extract().response();
	}
	
	public static Response getArtistAlbum(String artistId)
	{
		return given()
				.spec(SpecBuilder.getRequestSpec())
				.auth().oauth2(TokenManager.getToken())
				.pathParam("artistId", artistId)
				.when()
				.get(Routes.getArtistAlbum)
				.then().spec(SpecBuilder.getResponseSpec())
				.extract().response();
	}
	
	public static Response getArtistTopTrack(String artistId)
	{
		return given()
				.spec(SpecBuilder.getRequestSpec())
				.auth().oauth2(TokenManager.getToken())
				.pathParam("artistId", artistId)
				.when()
				.get(Routes.getArtistTopTrack)
				.then().spec(SpecBuilder.getResponseSpec())
				.extract().response();
	}
	
	public static Response getArtistsDetails(String artistIds)
	{
		return given()
				.spec(SpecBuilder.getRequestSpec())
				.auth().oauth2(TokenManager.getToken())
				.queryParam("ids",artistIds)
				.when()
				.get(Routes.getArtistsDetails)
				.then().spec(SpecBuilder.getResponseSpec())
				.extract().response();
	}
	
	public static Response getArtistRelatedArtist(String artistId)
	{
		return given()
				.spec(SpecBuilder.getRequestSpec())
				.auth().oauth2(TokenManager.getToken())
				.pathParam("artistId", artistId)
				.when()
				.get(Routes.getArtistRelatedArtist)
				.then().spec(SpecBuilder.getResponseSpec())
				.extract().response();
	}

}
