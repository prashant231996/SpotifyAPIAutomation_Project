package com.spotify.oauth2.applicationApi;

import io.restassured.response.Response;
import static io.restassured.RestAssured.given;

import com.spotify.oauth2.api.Routes;
import com.spotify.oauth2.api.SpecBuilder;
import com.spotify.oauth2.api.TokenManager;

public class ChapterApi {
	
	public static Response getChapterDetails(String chapterId)
	{
		return given()
				.spec(SpecBuilder.getRequestSpec())
				.auth().oauth2(TokenManager.getToken())
				.pathParam("chapterId", chapterId)
				.when()
				.get(Routes.getChapterDetail)
				.then().spec(SpecBuilder.getResponseSpec())
				.extract().response();
	}
	
	public static Response getChaptersDetails(String ids)
	{
		return given()
				.spec(SpecBuilder.getRequestSpec())
				.auth().oauth2(TokenManager.getToken())
				.queryParam("ids", ids)
				.when()
				.get(Routes.getChaptersDetail)
				.then().spec(SpecBuilder.getResponseSpec())
				.extract().response();
	}

}
