package com.spotify.oauth2.applicationApi;
import static io.restassured.RestAssured.given;

import com.spotify.oauth2.api.Routes;
import com.spotify.oauth2.api.SpecBuilder;
import com.spotify.oauth2.api.StatusCode;
import com.spotify.oauth2.api.TokenManager;
import io.restassured.response.Response;

public class UserAPI {

    public static Response getCurrantUserProfile()
    {
        return given()
                .spec(SpecBuilder.getRequestSpec())
                .auth().oauth2(TokenManager.getToken())
                .when()
                .get(Routes.getCurrantUserProfile)
                .then().spec(SpecBuilder.getResponseSpec())
                .assertThat().statusCode(StatusCode.CODE_200.code)
                .extract().response();
    }

    public static Response getUsersProfile(String userId)
    {
        return given().spec(SpecBuilder.getRequestSpec())
                .auth().oauth2(TokenManager.getToken())
                .pathParam("userId",userId)
                .when()
                .get(Routes.getUsersProfile)
                .then().spec(SpecBuilder.getResponseSpec())
                .assertThat().statusCode(StatusCode.CODE_200.code)
                .extract().response();
    }


}
