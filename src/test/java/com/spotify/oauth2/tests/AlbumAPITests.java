package com.spotify.oauth2.tests;

import static io.restassured.RestAssured.given;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.spotify.oauth2.api.Routes;
import com.spotify.oauth2.api.SpecBuilder;
import com.spotify.oauth2.api.StatusCode;
import com.spotify.oauth2.api.TokenManager;
import com.spotify.oauth2.applicationApi.AlbumApi;
import com.spotify.oauth2.pojo.Error;
import com.spotify.oauth2.pojo.ErrorRoot;
import com.spotify.oauth2.utils.PropertyUtils;

import io.restassured.response.Response;

public class AlbumAPITests {
	
	@Test(priority=1,description="User should be able to save the album")
	public static void userShouldAbleToSaveAlbum()
	{
		List<String>idList=new ArrayList<String>();
		idList.add("4iV5W9uYEdYUVa79Axb7Rh");
		idList.add("1301WleyT98MSxVHPZCA6M");
		HashMap<String,Object>hMap=new HashMap<String,Object>();
		hMap.put("ids",idList);
		Response res=AlbumApi.saveAlbum(hMap);
		Assert.assertEquals(res.getStatusCode(), StatusCode.CODE_200.code);
	}
	
	@Test(priority=2,description="User should able to get album details")
	public static void UserShouldAbleToGetAlubumDetails()
	{
		Response res=AlbumApi.getAlubum();
		Assert.assertEquals(res.getStatusCode(), StatusCode.CODE_200.code);
	}
	
	@Test(priority=3,description="User should able to resmove album details")
	public static void userShouldAbleToRemoveAlbumDetails()
	{
		List<String>idList=new ArrayList<String>();
		idList.add("4iV5W9uYEdYUVa79Axb7Rh");
		idList.add("1301WleyT98MSxVHPZCA6M");
		HashMap<String,Object>hMap=new HashMap<String,Object>();
		hMap.put("ids",idList);
		Response res=AlbumApi.removeAlbum(hMap);
		Assert.assertEquals(res.getStatusCode(), StatusCode.CODE_200.code);
	}
	
	@Test(priority=4,description="User should able to get saved album details")
	public static void userShouldAbleToGetAlbumDetails()
	{
		Response res=AlbumApi.usersSavedAlbum("4iV5W9uYEdYUVa79Axb7Rh");
		Assert.assertEquals(res.getStatusCode(), StatusCode.CODE_200.code);
		Assert.assertTrue(res.asString().contains("false"));
	}
	
	@Test(priority=5,description="User should not able to get album details with invalid token")
	public static void UserShouldNotAbleToGetAlubumDetailsWithInvalidToken()
	{
		Response res=AlbumApi.getAlubum("InvalidTokenValue");
		Assert.assertEquals(res.getStatusCode(), StatusCode.CODE_401.code);
		ErrorRoot error=res.as(ErrorRoot.class);
		Assert.assertEquals(error.getError().getMessage(), StatusCode.CODE_401.message);
	}
	
	@Test(priority=6,description="User should not able to get album details without passing token")
	public static void UserShouldNotAbleToGetAlubumDetailsWithoutToken()
	{
		Response res=given().spec(SpecBuilder.getRequestSpec())
				 .when()
				.get(Routes.getAlbum)
				.then().spec(SpecBuilder.getResponseSpec())
				.extract().response();
		Assert.assertEquals(res.getStatusCode(), StatusCode.CODE_401.code);
		ErrorRoot error=res.as(ErrorRoot.class);
		Assert.assertEquals(error.getError().getMessage(), StatusCode.CODE_401_withoutToken.message);
	}
	
	@Test(priority=7,description="User should able to get single album info")
	public static void UserShouldABleToGetSingleAlbumInfo() throws IOException
	{
		Response res=AlbumApi.getSingleAlbumInfo(PropertyUtils.getPropertyValue("albumId"));
		Assert.assertEquals(res.getStatusCode(), StatusCode.CODE_200.code);
		
	}
	
	@Test(priority=8,description="User should able to get several album info")
	public static void UserShouldABleToGetSeveralAlbumInfo() throws IOException
	{
		Response res=AlbumApi.getSeveralAlbumInfo(PropertyUtils.getPropertyValue("albumIds"));
		Assert.assertEquals(res.getStatusCode(), StatusCode.CODE_200.code);	
	}
	
	@Test(priority=9,description="User should able to get album track details")
	public static void UserShouldABleToGetAlbumTrackInfo() throws IOException
	{
		Response res=AlbumApi.getAlbumTrack(PropertyUtils.getPropertyValue("albumId"));
		Assert.assertEquals(res.getStatusCode(), StatusCode.CODE_200.code);	
	}
	
	@Test(priority=10,description="User should able to get New releases details")
	public static void UserShouldABleToGetNewRelease() throws IOException
	{
		Response res=AlbumApi.getNewRelease();
		Assert.assertEquals(res.getStatusCode(), StatusCode.CODE_200.code);	
	}
	

}
