package com.spotify.oauth2.tests;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.spotify.oauth2.api.StatusCode;
import com.spotify.oauth2.applicationApi.PlayerApi;
import com.spotify.oauth2.pojo.ErrorRoot;
import com.spotify.oauth2.utils.PropertyUtils;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class PlayerAPITest {
	
	@Test(priority=1,description="Add an item to the end of the user's current playback queue.")
	public void addAnItemToPlayBackQueue() throws IOException
	{
		Response response=PlayerApi.addItemToPlayBackQueue(PropertyUtils.getPropertyValue("uri"));
		ErrorRoot errorRoot=response.as(ErrorRoot.class);
		Assert.assertEquals(errorRoot.getError().getStatus(),StatusCode.CODE_403.code);
		Assert.assertEquals(errorRoot.getError().getMessage(), StatusCode.CODE_403.message);
		Assert.assertEquals(errorRoot.getError().getReason(),StatusCode.CODE_403.reason);
	}
	
	@Test(priority=2,description="Get information about the user’s current playback state")
	public void getPlaybackState()
	{
		Response response=PlayerApi.getPlaybackState();
		Assert.assertEquals(response.getStatusCode(), StatusCode.CODE_204.code);
	}
	
	@Test(priority=3,description="Transfer playback to a new device and optionally begin playback.")
	public void transferPlayBack() throws IOException
	{
		List<String>deviceIdList=new ArrayList<String>();
		deviceIdList.add(PropertyUtils.getPropertyValue("deviceId"));
		Response response=PlayerApi.transferAndPlayBack(deviceIdList);
		ErrorRoot errorRoot=response.as(ErrorRoot.class);
		Assert.assertEquals(errorRoot.getError().getStatus(),StatusCode.CODE_403.code);
		Assert.assertEquals(errorRoot.getError().getMessage(), StatusCode.CODE_403.message);
		Assert.assertEquals(errorRoot.getError().getReason(),StatusCode.CODE_403.reason);
	}
	
	@Test(priority=4,description="Get information about a user’s available Spotify Connect devices.")
	public void getAvailableDevices() throws IOException
	{
		Response response=PlayerApi.getAvailableDevices();
		Assert.assertEquals(response.getStatusCode(), StatusCode.CODE_200.code);
		//Verifying empty devices list from response
		JsonPath jsonPath=new JsonPath(response.asString());
		List<Object>responseList=jsonPath.get("devices");
		Assert.assertTrue(responseList.isEmpty());
	}

}
