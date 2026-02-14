package com.spotify.oauth2.tests;

import com.spotify.oauth2.applicationApi.UserAPI;
import com.spotify.oauth2.utils.PropertyUtils;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

public class UserAPITest {

    public static String userId;

    @Test(description = "Get user profile details",priority = 0,enabled = true)
    public void validateUserProfileDetails() throws IOException {
        Response response= UserAPI.getCurrantUserProfile();
        JsonPath js=new JsonPath(response.asString());
        userId=js.get("id");
        Assert.assertEquals(js.get("display_name"), PropertyUtils.getPropertyValue("displayName"));
    }

    @Test(description = "Get user profile details",priority = 1,enabled = true,dependsOnMethods = {"validateUserProfileDetails"})
    public void getUserProfileDetails() throws IOException {
        Response response= UserAPI.getUsersProfile(userId);
        JsonPath js=new JsonPath(response.asString());
        Assert.assertEquals(js.get("display_name"), PropertyUtils.getPropertyValue("displayName"));
    }

}
