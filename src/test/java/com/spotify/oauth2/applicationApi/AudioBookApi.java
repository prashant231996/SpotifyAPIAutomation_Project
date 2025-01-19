package com.spotify.oauth2.applicationApi;

import io.restassured.response.Response;
import static io.restassured.RestAssured.given;

import com.spotify.oauth2.api.Routes;
import com.spotify.oauth2.api.SpecBuilder;
import com.spotify.oauth2.api.TokenManager;

public class AudioBookApi {
	
	public static Response saveAudioBooks(String audioBookIds)
	{
		return given()
				.spec(SpecBuilder.getRequestSpec())
				.auth().oauth2(TokenManager.getToken())
				.queryParam("ids", audioBookIds)
				.when()
				.put(Routes.saveAudioBooks)
				.then().spec(SpecBuilder.getResponseSpec())
				.extract().response();
	}
	
	public static Response getAnAudioBook(String audioBookId)
	{
		return given()
				.spec(SpecBuilder.getRequestSpec())
				.auth().oauth2(TokenManager.getToken())
				.pathParam("audioBookId", audioBookId)
				.when()
				.get(Routes.getAnAudioBook)
				.then().spec(SpecBuilder.getResponseSpec())
				.extract().response();
	}

}
