package com.spotify.oauth2.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.spotify.oauth2.pojo.ErrorRoot;
import com.spotify.oauth2.pojo.Playlist;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;

public class PlaylistTests {
	
	RequestSpecification requestSpecification;
	
	ResponseSpecification responsSpecification;
	
	String access_token="BQADELb5yPKfUTfEZmKCk0ZND1vIWAq4D6OLoHaHDGxkfVn8jWly02Kh8aGfiQR7Rnu7cnrTJLCv6yG9URzHpYegYmum7QVenB-XR7CBWWJoxT3bylQxHXzVefsCr3yGkY6lH_QDrRoffbxg4d0jlquIDNClKkG3G4IB9PWk65O4sAscXxnUcK_0-oy8SI4l1kzY5eAiKcthrTjBmNPTYVpOUMQ3qlue-O2BPJ8Ov39X1ZwwNA3kgGlQWjODVGoyYNpZ-pvMpJMja1qYgQYcSKYC";
	
	@BeforeClass
	public void beforeClass()
	{
		RequestSpecBuilder requestSpecBuilder=new RequestSpecBuilder()
				.setBaseUri("https://api.spotify.com")
				.setBasePath("/v1")
				.addHeader("Authorization", "Bearer "+access_token)
				.setContentType(ContentType.JSON)
				.log(LogDetail.ALL);
		requestSpecification=requestSpecBuilder.build();
		
		ResponseSpecBuilder responseSpecBuilder=new ResponseSpecBuilder()
				.log(LogDetail.ALL);
		
		responsSpecification=responseSpecBuilder.build();
	}
	
	@Test(enabled=false)
	public void shouldBeAbleToCreatePlaylist()
	{
		Playlist reqPlaylist=new Playlist();
		reqPlaylist.setName("New Playlist");
		reqPlaylist.setDescription("New playlist description");
		reqPlaylist.setPublic(false);
		
		Playlist resPlaylist=given()
		.spec(requestSpecification)
		.body(reqPlaylist)
		.when()
		.post("/users/31hyf3r6jk76muofi2k2tccsw27m/playlists")
		.then().spec(responsSpecification)
		.assertThat().statusCode(201).extract().response().as(Playlist.class);
		
		Assert.assertEquals(resPlaylist.getName(), reqPlaylist.getName());
		Assert.assertEquals(resPlaylist.getDescription(), reqPlaylist.getDescription());
		Assert.assertEquals(resPlaylist.getPublic(), reqPlaylist.getPublic());
	}
	
	@Test(enabled=false)
	public void shouldBeAbleToGetPlaylist()
	{
		Playlist resPlaylist=given().spec(requestSpecification)
		.when()
		.get("/playlists/6Jwdl02e2BNsIaFCfyY23N")
		.then().spec(responsSpecification)
		.assertThat().statusCode(200).extract().response().as(Playlist.class);
		
		Assert.assertEquals(resPlaylist.getName(), "Updated Playlist Name");
		Assert.assertEquals(resPlaylist.getDescription(),"Updated playlist description");
		Assert.assertTrue(resPlaylist.getPublic());
	}
	
	@Test(enabled=false)
	public void shouldBeAbleToUpdatePlaylist()
	{
		Playlist reqPlaylist=new Playlist();
		reqPlaylist.setName("Updated Playlist Name");
		reqPlaylist.setDescription("Updated playlist description");
		reqPlaylist.setPublic(false);

		Playlist resPlaylist=given().spec(requestSpecification)
		.body(reqPlaylist)
		.when()
		.put("/playlists/6Jwdl02e2BNsIaFCfyY23N")
		.then().spec(responsSpecification)
		.assertThat().statusCode(200).extract().response().as(Playlist.class);
		
	}
	
	@Test(enabled=false)
	public void shouldNotBeAbleToCreatePlaylist()
	{
		Playlist reqPlaylist=new Playlist();
		reqPlaylist.setName("");
		reqPlaylist.setDescription("New playlist description");
		reqPlaylist.setPublic(false);
		
		ErrorRoot resError=given().spec(requestSpecification)
		.body(reqPlaylist)
		.when()
		.post("/users/31hyf3r6jk76muofi2k2tccsw27m/playlists")
		.then().spec(responsSpecification)
		.assertThat().statusCode(400).extract().response().as(ErrorRoot.class);
		
		Assert.assertEquals(resError.getError().getMessage(), "Missing required field: name");
		Assert.assertEquals(resError.getError().getStatus(), 400);
	}
	
	@Test(enabled=true)
	public void shouldNotBeAbleToCreatePlaylistWithTampperdOrExpiredToken()
	{
		Playlist reqPlaylist=new Playlist();
		reqPlaylist.setName("");
		reqPlaylist.setDescription("New playlist description");
		reqPlaylist.setPublic(false);
		
		ErrorRoot resError=given()
		.baseUri("https://api.spotify.com")
		.basePath("/v1")
		.header("Authorization", "Bearer "+"12345")
		.contentType(ContentType.JSON)
		.log().all()
		.body(reqPlaylist)
		.when()
		.post("/users/31hyf3r6jk76muofi2k2tccsw27m/playlists")
		.then().spec(responsSpecification)
		.assertThat().statusCode(401).extract().response().as(ErrorRoot.class);
		
		Assert.assertEquals(resError.getError().getMessage(), "Invalid access token");
		Assert.assertEquals(resError.getError().getStatus(), 401);
	}
	
	

}
