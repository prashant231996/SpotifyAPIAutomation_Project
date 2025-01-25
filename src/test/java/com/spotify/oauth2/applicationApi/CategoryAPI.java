package com.spotify.oauth2.applicationApi;

import static io.restassured.RestAssured.given;

import com.spotify.oauth2.api.Routes;
import com.spotify.oauth2.api.SpecBuilder;
import com.spotify.oauth2.api.TokenManager;

import io.restassured.response.Response;

public class CategoryAPI {
	
	public static Response getSevaralCategoryDetails()
	{
		return given()
				.spec(SpecBuilder.getRequestSpec())
				.auth().oauth2(TokenManager.getToken())
				.when()
				.get(Routes.getSevaralCategories)
				.then().spec(SpecBuilder.getResponseSpec())
				.extract().response();
	}
	
	public static Response getSingleCategoryDetails(String categoryId)
	{
		return given()
				.spec(SpecBuilder.getRequestSpec())
				.auth().oauth2(TokenManager.getToken())
				.pathParam("categoryId", categoryId)
				.when()
				.get(Routes.getSingleCategory)
				.then().spec(SpecBuilder.getResponseSpec())
				.extract().response();
	}

}
