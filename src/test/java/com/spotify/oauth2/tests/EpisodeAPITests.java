package com.spotify.oauth2.tests;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.spotify.oauth2.api.StatusCode;
import com.spotify.oauth2.applicationApi.EpisodeApi;
import com.spotify.oauth2.pojo.ErrorRoot;
import com.spotify.oauth2.utils.PropertyUtils;

import io.restassured.response.Response;

public class EpisodeAPITests {
	
	@Test(priority=1,description="Get episode details.")
	public void getEpisodeDetails() throws IOException
	{
		Response res=EpisodeApi.getEpisodeDetails(PropertyUtils.getPropertyValue("episodeId"));
		//Verifying status code
		Assert.assertEquals(res.getStatusCode(), StatusCode.CODE_404_ResourceNF.code);
		//Verifying response body message
		ErrorRoot errorRoot=res.as(ErrorRoot.class);
		Assert.assertEquals(errorRoot.getError().getMessage(), StatusCode.CODE_404_ResourceNF.message);
	}
	
	@Test(priority=2,description="Get sevaral episode details.")
	public void getSevaralEpisodeDetails() throws IOException
	{
		Response res=EpisodeApi.getSevaralEpisodeDetails(PropertyUtils.getPropertyValue("episodeIds"));
		//Verifying status code
		Assert.assertEquals(res.getStatusCode(),StatusCode.CODE_200.code);
		//Verifying eoisodes key from response body message
		Assert.assertTrue(res.asString().contains("episodes"));
	}
	
	@Test(priority=3,description="Save episode details.")
	public void saveEpisodeDetails() throws IOException
	{
		List<String>episodeList=new ArrayList<String>();
		episodeList.add(PropertyUtils.getPropertyValue("episodeId"));
		Response res=EpisodeApi.saveEpisode(episodeList);
		//Verifying status code
		Assert.assertEquals(res.getStatusCode(),StatusCode.CODE_200.code);
	}

}
