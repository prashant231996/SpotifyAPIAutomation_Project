package com.spotify.oauth2.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.spotify.oauth2.api.StatusCode;
import com.spotify.oauth2.applicationApi.CategoryAPI;
import com.spotify.oauth2.utils.PropertyUtils;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class CategoryAPITests {
	
	@Test(priority=1, description="Get sevaral categories")
	public void getSevaralCategoryDetails()
	{
		try
		{
			Response res=CategoryAPI.getSevaralCategoryDetails();
			//Verifying the response code
			Assert.assertEquals(res.getStatusCode(), StatusCode.CODE_200.code);
			//Verifying from response contains categories key in them
			Assert.assertTrue(res.asString().contains("categories"));
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	@Test(priority=2,description="Get single category details")
	public void getSingleCategoryDetails()
	{
		try
		{
			Response res=CategoryAPI.getSingleCategoryDetails(PropertyUtils.getPropertyValue("categoryId"));
			//Verifying status code
			Assert.assertEquals(res.getStatusCode(), StatusCode.CODE_200.code);
			//Verifying response body contains expected name
			JsonPath js=new JsonPath(res.asString());
			Assert.assertEquals(js.get("name").toString(), "Cooking & Dining");
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}

}
