package com.spotify.oauth2.tests;

import com.spotify.oauth2.api.StatusCode;
import com.spotify.oauth2.applicationApi.MarketApi;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class MarketAPITest {

    @Test(description = "Get Available Markets",priority = 0)
    public void getAvailableMarket()
    {
        Response response= MarketApi.getAvailableMarkets();
        Assert.assertEquals(response.getStatusCode(), StatusCode.CODE_200.code);
    }

}
