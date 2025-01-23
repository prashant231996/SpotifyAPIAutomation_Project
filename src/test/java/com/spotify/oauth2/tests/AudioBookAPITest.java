package com.spotify.oauth2.tests;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.spotify.oauth2.api.StatusCode;
import com.spotify.oauth2.applicationApi.AudioBookApi;
import com.spotify.oauth2.pojo.ErrorRoot;
import com.spotify.oauth2.utils.PropertyUtils;

import io.cucumber.core.internal.com.fasterxml.jackson.databind.annotation.JsonAppend.Prop;
import io.restassured.response.Response;

public class AudioBookAPITest {
	
	@Test(priority=1,description="Save audio books")
	public void saveAudioBooks()
	{
		try
		{
			Response res=AudioBookApi.saveAudioBooks(PropertyUtils.getPropertyValue("audioBookIds"));
			//Validating respective status code
			Assert.assertEquals(res.getStatusCode(), StatusCode.CODE_200.code);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	@Test(priority=2,description="Get an audio book")
	public void getAnAudioBookDetails()
	{
		try
		{
			Response res=AudioBookApi.getAnAudioBook(PropertyUtils.getPropertyValue("audioBookId"));
			//Validating status code 404 as no audio book is available
			ErrorRoot errRoot=res.as(ErrorRoot.class);
			Assert.assertEquals(errRoot.getError().getStatus(), StatusCode.CODE_404_ResourceNF.code);
			Assert.assertEquals(errRoot.getError().getMessage(), StatusCode.CODE_404_ResourceNF.message);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	@Test(priority=3,description="Check Users saved audio books")
	public void chkUsersSavedAudioBooks() throws IOException
	{
		Response res=AudioBookApi.chkUsersSavedAudioBooks(PropertyUtils.getPropertyValue("audioBookIds"));
		Assert.assertEquals(res.getStatusCode(), StatusCode.CODE_200.code);
	}
	
	@Test(priority=4, description="Get sevaral audion books")
	public void getSevaralAudioBooks() throws IOException
	{
		Response res=AudioBookApi.getSevaralAudioBooks(PropertyUtils.getPropertyValue("audioBookIds"));
		Assert.assertEquals(res.getStatusCode(), StatusCode.CODE_200.code);
		//Verifying response contains audiobooks
		Assert.assertTrue(res.asString().contains("audiobooks"));
	}
	
	@Test(priority=5,description="Get audio book chapter")
	public void getAudioBookChapter() throws IOException
	{
		Response res=AudioBookApi.getAudioBookChapter(PropertyUtils.getPropertyValue("audioBookId"));
		Assert.assertEquals(res.getStatusCode(), StatusCode.CODE_404_ResourceNF.code);
		ErrorRoot rootErr=res.as(ErrorRoot.class);
		Assert.assertEquals(rootErr.getError().getMessage(), StatusCode.CODE_404_ResourceNF.message);
	}
	
	@Test(priority=6,description="Get users saved audio books")
	public void getUsersSavedAudioBooks()
	{
		Response res=AudioBookApi.getUsersSavedAudioBook();
		Assert.assertEquals(res.getStatusCode(), StatusCode.CODE_200.code);
	}
	
	@Test(priority=7,description="Remove Users saved audio books")
	public void removeUsersSavedAudioBook() throws IOException
	{
		Response res=AudioBookApi.removeUsersSavedAudioBooks(PropertyUtils.getPropertyValue("audioBookIds"));
		Assert.assertEquals(res.getStatusCode(), StatusCode.CODE_200.code);
	}
	
	

}
