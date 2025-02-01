package com.spotify.oauth2.applicationApi;

import io.restassured.response.Response;
import static io.restassured.RestAssured.given;

import java.util.HashMap;
import java.util.List;

import com.spotify.oauth2.api.Routes;
import com.spotify.oauth2.api.SpecBuilder;
import com.spotify.oauth2.api.TokenManager;

public class EpisodeApi {
	
	public static Response getEpisodeDetails(String episodeId)
	{
		return given()
				.spec(SpecBuilder.getRequestSpec())
				.auth().oauth2(TokenManager.getToken())
				.pathParam("episodeId", episodeId)
				.when()
				.get(Routes.getEpisodeDetail)
				.then().spec(SpecBuilder.getResponseSpec())
				.extract().response();
	}
	
	public static Response getSevaralEpisodeDetails(String episodeIds)
	{
		return given()
				.spec(SpecBuilder.getRequestSpec())
				.auth().oauth2(TokenManager.getToken())
				.queryParam("ids", episodeIds)
				.when()
				.get(Routes.getSevaralEpisods)
				.then().spec(SpecBuilder.getResponseSpec())
				.extract().response();
	}
	
	public static Response saveEpisode(List<String>episodeIds)
	{
		HashMap<String,Object>bodyMap=new HashMap<String,Object>();
		bodyMap.put("ids",episodeIds);
		return given()
				.spec(SpecBuilder.getRequestSpec())
				.auth().oauth2(TokenManager.getToken())
				.body(bodyMap)
				.when()
				.put(Routes.saveEpisode)
				.then().spec(SpecBuilder.getResponseSpec())
				.extract().response();
	}
	
	

}
