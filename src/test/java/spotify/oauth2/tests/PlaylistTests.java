package spotify.oauth2.tests;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class PlaylistTests {
	
	RequestSpecification requestSpecification;
	
	ResponseSpecification responsSpecification;
	
	String access_token="BQCXiBYclWzslEIhgTj-Ya0FrZQERh3BzOnIaA3iny7P0PQkYLdQ7RdCDOPyse5cXRolIBdLeNufXk2btKTXQLey_QULGv9QBk0Bpn1gHSskGEVYD18N9Pq9ElLznBqKOmoc5Z-VZu1zVud9X_D1ysFKl1g8sMjEuAiDlask__qUQl-4MHm8oI5LPwLASkoVVCWVUGGRxbGSjeAzUNah9lc3m6cDhXkB7iRzTLafdc9pJGVfBwNOT0FAnTRCEFXzYxRgMstVM9DkUjajaYWjv8T2";
	
	@BeforeClass
	public void beforeClass()
	{
		RequestSpecBuilder requestSpecBuilder=new RequestSpecBuilder()
				.setBaseUri("https://api.spotify.com")
				.setBasePath("/v1")
				.addHeader("Authorization", "Bearer "+access_token)
				.setContentType(ContentType.JSON)
				.log(LogDetail.ALL);
		requestSpecification=requestSpecBuilder.build();
		
		ResponseSpecBuilder responseSpecBuilder=new ResponseSpecBuilder()
				.expectContentType(ContentType.JSON)
				.log(LogDetail.ALL);
		
		responsSpecification=responseSpecBuilder.build();
	}
	
	@Test(enabled=false)
	public void shouldBeAbleToCreatePlaylist()
	{
		String payload="{\r\n" + 
				"    \"name\": \"New Playlist\",\r\n" + 
				"    \"description\": \"New playlist description\",\r\n" + 
				"    \"public\": false\r\n" + 
				"}";
		given().spec(requestSpecification)
		.body(payload)
		.when()
		.post("/users/31hyf3r6jk76muofi2k2tccsw27m/playlists")
		.then().spec(responsSpecification)
		.assertThat().statusCode(201)
		.body("name", equalTo("New Playlist"),
				"description",equalTo("New playlist description"),
				"public", equalTo(false));
	}
	
	@Test(enabled=false)
	public void shouldBeAbleToGetPlaylist()
	{
		given().spec(requestSpecification)
		.when()
		.get("/playlists/76EZU4tkyTfmMslWib2PGm")
		.then().spec(responsSpecification)
		.assertThat().statusCode(200)
		.body("name", equalTo("New Playlist"),
				"description",equalTo("New playlist description"),
				"public", equalTo(true));	
	}
	
	@Test(enabled=true)
	public void shouldBeAbleToUpdatePlaylist()
	{
		String payLoad="{\r\n" + 
				"    \"name\": \"Updated Playlist Name\",\r\n" + 
				"    \"description\": \"Updated playlist description\",\r\n" + 
				"    \"public\": false\r\n" + 
				"}";
		given().spec(requestSpecification)
		.body(payLoad)
		.when()
		.put("/playlists/76EZU4tkyTfmMslWib2PGm")
		.then().spec(responsSpecification)
		.assertThat().statusCode(200);
	}
	
	

}
