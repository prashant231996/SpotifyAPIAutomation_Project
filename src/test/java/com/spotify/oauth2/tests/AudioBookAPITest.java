package com.spotify.oauth2.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.spotify.oauth2.api.StatusCode;
import com.spotify.oauth2.applicationApi.AudioBookApi;
import com.spotify.oauth2.pojo.ErrorRoot;
import com.spotify.oauth2.utils.PropertyUtils;

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
	
	

}
