package com.spotify.oauth2.applicationApi;

import com.spotify.oauth2.api.Routes;
import com.spotify.oauth2.api.SpecBuilder;
import com.spotify.oauth2.api.TokenManager;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;

public class MarketApi {

    public static Response getAvailableMarkets()
    {
        return given()
                .spec(SpecBuilder.getRequestSpec())
                .auth().oauth2(TokenManager.getToken())
                .when()
                .get(Routes.getAvailableMarkets)
                .then()
                .spec(SpecBuilder.getResponseSpec())
                .extract().response();
    }

}
