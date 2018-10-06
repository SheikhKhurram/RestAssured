package Testcases;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import TestBase.GettingSessionID;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;
public class AddingComment extends GettingSessionID 
{
    String bugID ;
	@BeforeMethod
	public void setUp()
	{
		GettingSessionID.gettingSessionID();
		GettingSessionID.initializing();
		bugID = CreatingBug.creatingBug();
	}
	
	@Test
	public void commentingOnBug()
	{
		RestAssured.baseURI = prop.getProperty("URI");
		Response response = 
				given().headers("Content-Type" , "application/json" , "Cookie" , GettingSessionID.gettingSessionID()).
				body("{\r\n" + 
						"    \"body\": \"Lorem ipsum dolor sit amet, consectetur adipiscing elit. Pellentesque eget venenatis elit. Duis eu justo eget augue iaculis fermentum. Sed semper quam laoreet nisi egestas at posuere augue semper.\"\r\n" + 
						"}").
				when().
				post("/rest/api/2/issue/"+bugID+"/comment").
				then().
				assertThat().statusCode(201).extract().response();
		
		JsonPath json = response.jsonPath();
		
		System.out.println(response.asString());
	}
	
	
}
