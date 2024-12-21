package com.spotify.oauth2.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.spotify.oauth2.api.Routes;
import com.spotify.oauth2.api.SpecBuilder;
import com.spotify.oauth2.applicationApi.PlaylistApi;
import com.spotify.oauth2.pojo.ErrorRoot;
import com.spotify.oauth2.pojo.Playlist;
import com.spotify.oauth2.utils.PropertyUtils;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import java.io.IOException;


public class PlaylistTests {
	
	public static Playlist resPlaylist;
	
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
	
	public void assertError(ErrorRoot resError,String expectedErrorMsg, int expectedStausCode)
	{
		Assert.assertEquals(resError.getError().getMessage(), expectedErrorMsg);
		Assert.assertEquals(resError.getError().getStatus(), expectedStausCode);
	}
	
	@Test(enabled=true,priority=1)
	public void shouldBeAbleToCreatePlaylist() throws IOException
	{
		Playlist reqPlaylist=playListBuilder("New Playlist", "New playlist description", false);
		Response res=PlaylistApi.createPlaylist(reqPlaylist,PropertyUtils.getPropertyValue("userId"));
		Assert.assertEquals(res.getStatusCode(), 201);
	    resPlaylist=res.as(Playlist.class);
	    assertPlayList(resPlaylist, reqPlaylist);
	}
	
	@Test(enabled=true,priority=2)
	public void shouldBeAbleToGetPlaylist()
	{
		Response res=PlaylistApi.getPlaylist(resPlaylist.getId());
		Assert.assertEquals(res.getStatusCode(), 200);
		Playlist resPlaylist=res.as(Playlist.class);
		Assert.assertEquals(resPlaylist.getName(), "New Playlist");
		Assert.assertEquals(resPlaylist.getDescription(),"New playlist description");
		Assert.assertTrue(resPlaylist.getPublic());
	}
	
	@Test(enabled=true,priority=3)
	public void shouldBeAbleToUpdatePlaylist()
	{
		Playlist reqPlaylist=playListBuilder("Updated Playlist Name", "Updated playlist description", false);
		Response res=PlaylistApi.updatePlaylist(resPlaylist.getId(), reqPlaylist);
		Assert.assertEquals(res.getStatusCode(), 200);	
	}
	
	@Test(enabled=true,priority=4)
	public void shouldNotBeAbleToCreatePlaylist() throws IOException
	{
		Playlist reqPlaylist=playListBuilder("", "Updated playlist description", false);	
		Response res=PlaylistApi.createPlaylist(reqPlaylist, PropertyUtils.getPropertyValue("userId"));
		Assert.assertEquals(res.getStatusCode(), 400);
		ErrorRoot resError=res.as(ErrorRoot.class);
		assertError(resError,   "Missing required field: name", 400);
	}
	
	@Test(enabled=true,priority=5)
	public void shouldNotBeAbleToCreatePlaylistWithTampperdOrExpiredToken() throws IOException
	{
		Playlist reqPlaylist=playListBuilder("New Playlist Name", "New playlist description", false);	
		Response res=PlaylistApi.createPlaylist(reqPlaylist, PropertyUtils.getPropertyValue("userId"),"Invalid_Token_12345");
		Assert.assertEquals(res.getStatusCode(), 401);
		ErrorRoot resError=res.as(ErrorRoot.class);
		assertError(resError,  "Invalid access token", 401);
	}
	
	

}
