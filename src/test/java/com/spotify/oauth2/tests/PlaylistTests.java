package com.spotify.oauth2.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.spotify.oauth2.api.Routes;
import com.spotify.oauth2.api.SpecBuilder;
import com.spotify.oauth2.api.StatusCode;
import com.spotify.oauth2.applicationApi.PlaylistApi;
import com.spotify.oauth2.pojo.ErrorRoot;
import com.spotify.oauth2.pojo.Playlist;
import com.spotify.oauth2.utils.FakerUtils;
import com.spotify.oauth2.utils.PropertyUtils;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class PlaylistTests {
	
	public static Playlist reqPlaylist;
	public static Playlist resPlaylist;
	public static String snapShotId;
	
	public Playlist playListBuilder(String name, String description, boolean _public)
	{
		Playlist reqPlaylist=new Playlist();
		reqPlaylist.setName(name);
		reqPlaylist.setDescription(description);
		reqPlaylist.setPublic(_public);
		return reqPlaylist;
	}
	
	public void assertPlayList(Playlist resPlaylist, Playlist reqPlaylist)
	{
		Assert.assertEquals(resPlaylist.getName(), reqPlaylist.getName());
		Assert.assertEquals(resPlaylist.getDescription(), reqPlaylist.getDescription());
		Assert.assertEquals(resPlaylist.getPublic(), reqPlaylist.getPublic());
	}
	
	public void assertError(ErrorRoot resError,StatusCode statusCode)
	{
		Assert.assertEquals(resError.getError().getMessage(), statusCode.message);
		Assert.assertEquals(resError.getError().getStatus(), statusCode.code);
	}
	
	@Test(enabled=true,priority=1)
	public void shouldBeAbleToCreatePlaylist() throws IOException
	{
		reqPlaylist=playListBuilder(FakerUtils.generateName(), FakerUtils.generateDescription(), false);
		Response res=PlaylistApi.createPlaylist(reqPlaylist,PropertyUtils.getPropertyValue("userId"));
		Assert.assertEquals(res.getStatusCode(), StatusCode.CODE_201.code);
	    resPlaylist=res.as(Playlist.class);
	    assertPlayList(resPlaylist, reqPlaylist);
	}
	
	@Test(enabled=true,priority=2)
	public void shouldBeAbleToGetPlaylist()
	{
		Response res=PlaylistApi.getPlaylist(resPlaylist.getId());
		Assert.assertEquals(res.getStatusCode(), StatusCode.CODE_200.code);
		//Playlist getreSPlaylist=res.as(Playlist.class);
		//assertPlayList(getreSPlaylist, resPlaylist);
//		Assert.assertEquals(resPlaylist.getName(), "New Playlist");
//		Assert.assertEquals(resPlaylist.getDescription(),"New playlist description");
//		Assert.assertTrue(resPlaylist.getPublic());
	}
	
	@Test(enabled=true,priority=3)
	public void shouldBeAbleToUpdatePlaylist()
	{
		Playlist reqPlaylist=playListBuilder(FakerUtils.generateName(), FakerUtils.generateDescription(), false);
		Response res=PlaylistApi.updatePlaylist(resPlaylist.getId(), reqPlaylist);
		Assert.assertEquals(res.getStatusCode(),StatusCode.CODE_200.code );	
	}
	
	@Test(enabled=true,priority=4)
	public void shouldNotBeAbleToCreatePlaylist() throws IOException
	{
		Playlist reqPlaylist=playListBuilder("", FakerUtils.generateDescription(), false);	
		Response res=PlaylistApi.createPlaylist(reqPlaylist, PropertyUtils.getPropertyValue("userId"));
		Assert.assertEquals(res.getStatusCode(), StatusCode.CODE_400.code);
		ErrorRoot resError=res.as(ErrorRoot.class);
		assertError(resError, StatusCode.CODE_400);
	}
	
	@Test(enabled=true,priority=5)
	public void shouldNotBeAbleToCreatePlaylistWithTampperdOrExpiredToken() throws IOException
	{
		Playlist reqPlaylist=playListBuilder(FakerUtils.generateName(), FakerUtils.generateDescription(), false);	
		Response res=PlaylistApi.createPlaylist(reqPlaylist, PropertyUtils.getPropertyValue("userId"),"Invalid_Token_12345");
		Assert.assertEquals(res.getStatusCode(), StatusCode.CODE_401.code);
		ErrorRoot resError=res.as(ErrorRoot.class);
		assertError(resError,StatusCode.CODE_401);
	}
	
	@Test(enabled=true,priority=6)
	public void shouldBeAbleToAddItemDetails()
	{
		String itemDetails="{\"uris\": [\"spotify:track:4iV5W9uYEdYUVa79Axb7Rh\",\"spotify:track:1301WleyT98MSxVHPZCA6M\", \"spotify:episode:512ojhOuo1ktJprKbVcKyQ\"]}";
		Response res=PlaylistApi.addItemsToPlaylist(resPlaylist.getId(), itemDetails);
		Assert.assertEquals(res.getStatusCode(), StatusCode.CODE_201.code);
		JsonPath js=new JsonPath(res.asString());
		snapShotId=js.get("snapshot_id");
	}
	
	@Test(enabled=true,priority=7)
	public void shouldBeAbleToGetItemDetails()
	{
		Response res=PlaylistApi.getItemDetails(resPlaylist.getId());
		Assert.assertEquals(res.getStatusCode(), StatusCode.CODE_200.code);
	}
	
	@Test(enabled=true,priority=8)
	public void shouldBeAbleToUpdateItemDetails()
	{
		List<Object>uriList=new ArrayList<Object>();
		uriList.add("spotify:track:4iV5W9uYEdYUVa79Axb7Rh");
		uriList.add("spotify:track:1301WleyT98MSxVHPZCA6M");
		uriList.add("spotify:episode:512ojhOuo1ktJprKbVcKyQ");
		HashMap<String,Object>hmap=new HashMap<String, Object>();
		hmap.put("uris",uriList);
		Response res=PlaylistApi.updateItemDetails(resPlaylist.getId(), hmap);
		Assert.assertEquals(res.getStatusCode(), StatusCode.CODE_200.code);
	}
	
	@Test(enabled=true,priority=9)
	public void shouldBeABleToRemoveItemDetails()
	{
		List<Object>uriList=new ArrayList<Object>();
		uriList.add("spotify:track:4iV5W9uYEdYUVa79Axb7Rh");
		uriList.add("spotify:track:1301WleyT98MSxVHPZCA6M");
		uriList.add("spotify:episode:512ojhOuo1ktJprKbVcKyQ");
		HashMap<String,Object>urimap=new HashMap<String,Object>();
		urimap.put("uri","spotify:track:4iV5W9uYEdYUVa79Axb7Rh");
		List<Object>trackList=new ArrayList<Object>();
		trackList.add(urimap);
		HashMap<String,Object>hmap=new HashMap<String,Object>();
		hmap.put("tracks",trackList);
		hmap.put("snapshot_id",snapShotId);
		Response res=PlaylistApi.removeItemDetails(resPlaylist.getId(), hmap);
		Assert.assertEquals(res.getStatusCode(), StatusCode.CODE_200.code);
	}
	
	@Test(enabled=true,priority=10)
	public void getCurrantUserPlaylist()
	{
		Response res=PlaylistApi.getCurrantUserPlaylist();
		Assert.assertEquals(res.getStatusCode(), StatusCode.CODE_200.code);
	}
	
	@Test(enabled=true,priority=11)
	public void getUsersPlaylist() throws IOException
	{
		Response res=PlaylistApi.getUsersPlaylist(PropertyUtils.getPropertyValue("userId"));
		Assert.assertEquals(res.getStatusCode(), StatusCode.CODE_200.code);
	}
	
	

}
