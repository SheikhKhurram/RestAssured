package Testcases;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import TestBase.GettingSessionID;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class CreatingBug extends GettingSessionID
{
	@BeforeMethod
	public static void setUp()
	{
		GettingSessionID.initializing();
	}
	
	
   @Test
   public static String creatingBug()
   {
	   RestAssured.baseURI = prop.getProperty("URI");
	   Response response = given().
	   headers("Content-Type" , "application/json" , "Cookie" , GettingSessionID.gettingSessionID()).
	   body("{\r\n" + 
	   		"    \"fields\": {\r\n" + 
	   		"        \"project\": {\r\n" + 
	   		"            \"key\": \"RES\"\r\n" + 
	   		"        },\r\n" + 
	   		"        \"summary\": \"Creating this summary using RestAssured\",\r\n" + 
	   		"        \"issuetype\": {\r\n" + 
	   		"            \"name\": \"Bug\"\r\n" + 
	   		"        },\r\n" + 
	   		"        \"priority\": {\r\n" + 
	   		"            \"name\": \"High\"\r\n" + 
	   		"        },\r\n" + 
	   		"        \"description\": \"Hey there im creating this bug from the RestAssured\"\r\n" + 
	   		"    }\r\n" + 
	   		"}").
	   when().
	   post(prop.getProperty("CreatingBugURL")).
	   then().assertThat().statusCode(201).extract().response();
	  
	  JsonPath json = response.jsonPath();
	  
	  System.out.println("Issues ID " + json.get("key"));
	  
	  return json.get("key");
   }
}
