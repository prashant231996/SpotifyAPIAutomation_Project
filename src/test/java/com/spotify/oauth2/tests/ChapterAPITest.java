package com.spotify.oauth2.tests;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.spotify.oauth2.api.StatusCode;
import com.spotify.oauth2.applicationApi.ChapterApi;
import com.spotify.oauth2.pojo.ErrorRoot;
import com.spotify.oauth2.utils.PropertyUtils;

import io.restassured.response.Response;

public class ChapterAPITest {
	
	@Test(priority=1,description="Get chapter details")
	public void getChapterDetail() throws IOException
	{
		Response res=ChapterApi.getChapterDetails(PropertyUtils.getPropertyValue("chapterId"));
		//Asserting response 404 service not found/resource not found
		Assert.assertEquals(res.getStatusCode(), StatusCode.CODE_404_ResourceNF.code);
		//Asserting resource not found response message
		ErrorRoot errorRoot=res.as(ErrorRoot.class);
		Assert.assertEquals(errorRoot.getError().getMessage(), StatusCode.CODE_404_ResourceNF.message);
	}
	
	@Test(priority=2,description="Get chapters details")
	public void getChaptersDetails() throws IOException
	{
		Response res=ChapterApi.getChaptersDetails(PropertyUtils.getPropertyValue("chapterIds"));
		//Verifying response code 200
		Assert.assertEquals(res.getStatusCode(), StatusCode.CODE_200.code);
		//Verifying chapters from response body
		Assert.assertTrue(res.asString().contains("chapters"));
	}

}
