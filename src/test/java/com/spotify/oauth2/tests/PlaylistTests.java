package com.spotify.oauth2.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.spotify.oauth2.api.SpecBuilder;
import com.spotify.oauth2.applicationApi.PlaylistApi;
import com.spotify.oauth2.pojo.ErrorRoot;
import com.spotify.oauth2.pojo.Playlist;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;


public class PlaylistTests {
	
	String userId="31hyf3r6jk76muofi2k2tccsw27m";
	
	@Test(enabled=false)
	public void shouldBeAbleToCreatePlaylist()
	{
		Playlist reqPlaylist=new Playlist();
		reqPlaylist.setName("New Playlist");
		reqPlaylist.setDescription("New playlist description");
		reqPlaylist.setPublic(false);
		Response res=PlaylistApi.createPlaylist(reqPlaylist,userId);
		Assert.assertEquals(res.getStatusCode(), 201);
		Playlist resPlaylist=res.as(Playlist.class);
		Assert.assertEquals(resPlaylist.getName(), reqPlaylist.getName());
		Assert.assertEquals(resPlaylist.getDescription(), reqPlaylist.getDescription());
		Assert.assertEquals(resPlaylist.getPublic(), reqPlaylist.getPublic());
	}
	
	@Test(enabled=false)
	public void shouldBeAbleToGetPlaylist()
	{
		Response res=PlaylistApi.getPlaylist("6Jwdl02e2BNsIaFCfyY23N");
		Assert.assertEquals(res.getStatusCode(), 200);
		Playlist resPlaylist=res.as(Playlist.class);
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
		Response res=PlaylistApi.updatePlaylist("6Jwdl02e2BNsIaFCfyY23N", reqPlaylist);
		Assert.assertEquals(res.getStatusCode(), 200);	
	}
	
	@Test(enabled=false)
	public void shouldNotBeAbleToCreatePlaylist()
	{
		Playlist reqPlaylist=new Playlist();
		reqPlaylist.setName("");
		reqPlaylist.setDescription("New playlist description");
		reqPlaylist.setPublic(false);
		
		Response res=PlaylistApi.createPlaylist(reqPlaylist, userId);
		Assert.assertEquals(res.getStatusCode(), 400);
		ErrorRoot resError=res.as(ErrorRoot.class);
		
		Assert.assertEquals(resError.getError().getMessage(), "Missing required field: name");
		Assert.assertEquals(resError.getError().getStatus(), 400);
	}
	
	@Test(enabled=true)
	public void shouldNotBeAbleToCreatePlaylistWithTampperdOrExpiredToken()
	{
		Playlist reqPlaylist=new Playlist();
		reqPlaylist.setName("New Playlist Name");
		reqPlaylist.setDescription("New playlist description");
		reqPlaylist.setPublic(false);
		
		Response res=PlaylistApi.createPlaylist(reqPlaylist, userId,"Invalid_Token_12345");
		Assert.assertEquals(res.getStatusCode(), 401);
		ErrorRoot resError=res.as(ErrorRoot.class);
		
		Assert.assertEquals(resError.getError().getMessage(), "Invalid access token");
		Assert.assertEquals(resError.getError().getStatus(), 401);
	}
	
	

}
