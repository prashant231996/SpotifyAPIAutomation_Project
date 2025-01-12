package com.spotify.oauth2.tests;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.fasterxml.jackson.databind.annotation.JsonAppend.Prop;
import com.spotify.oauth2.api.StatusCode;
import com.spotify.oauth2.applicationApi.ArtistApi;
import com.spotify.oauth2.pojo.ErrorRoot;
import com.spotify.oauth2.utils.PropertyUtils;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class ArtistAPITests {
	
	@Test(priority=1,description="Get artist details")
	public void getArtistDetails() throws IOException
	{
		Response res=ArtistApi.getArtistDetails(PropertyUtils.getPropertyValue("artistId"));
		//Verifying status code
		Assert.assertEquals(res.getStatusCode(), StatusCode.CODE_200.code);
		//Verifying id from response is same as what we passed in path parameter
		JsonPath js=new JsonPath(res.asString());
		Assert.assertEquals(js.get("id").toString(), PropertyUtils.getPropertyValue("artistId"));
		//Verifying name of artist from response message
		Assert.assertEquals(js.get("name"), PropertyUtils.getPropertyValue("artistName"));
	}
	
	@Test(priority=2,description="Get artist album details")
	public void getArtistAlbumDetails() throws IOException
	{
		Response res=ArtistApi.getArtistAlbum(PropertyUtils.getPropertyValue("artistId"));
		//Verifying status code
		Assert.assertEquals(res.getStatusCode(), StatusCode.CODE_200.code);
	}
	
	@Test(priority=3,description="Get artist top track details")
	public void getArtistTopTrack() throws IOException
	{
		Response res=ArtistApi.getArtistTopTrack(PropertyUtils.getPropertyValue("artistId"));
		//Verifying status code
		Assert.assertEquals(res.getStatusCode(), StatusCode.CODE_200.code);
		//Veryfying response contains tracks in them
	    Assert.assertTrue(res.asString().contains("tracks"));
	}
	
	@Test(priority=4,description="Get artists details")
	public void getArtistsDetails() throws IOException
	{
		Response res=ArtistApi.getArtistsDetails(PropertyUtils.getPropertyValue("artistId"));
		//Verifying status code
		Assert.assertEquals(res.getStatusCode(), StatusCode.CODE_200.code);
		//Veryfying response contains artists in them
	    Assert.assertTrue(res.asString().contains("artists"));
	}
	
	@Test(priority=5,description="Get artists related artist details")
	public void getArtistsRelatedArtistDetails() throws IOException
	{
		Response res=ArtistApi.getArtistRelatedArtist(PropertyUtils.getPropertyValue("artistId"));
		//Verifying status code
		Assert.assertEquals(res.getStatusCode(), StatusCode.CODE_404.code);
		//Validating error message by storing message into error pojo class
		ErrorRoot rootErr=res.as(ErrorRoot.class);
		Assert.assertEquals(rootErr.getError().getStatus(), StatusCode.CODE_404.code);
		Assert.assertEquals(rootErr.getError().getMessage(), StatusCode.CODE_404.message);
		
	}
	
	

}
