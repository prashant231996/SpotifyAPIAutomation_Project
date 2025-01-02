package com.spotify.oauth2.tests;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.spotify.oauth2.api.StatusCode;
import com.spotify.oauth2.applicationApi.AlbumApi;

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

}
