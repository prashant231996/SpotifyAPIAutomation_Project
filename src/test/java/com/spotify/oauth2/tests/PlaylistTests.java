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
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import java.io.IOException;


public class PlaylistTests {
	
	public static Playlist reqPlaylist;
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
	
	

}
