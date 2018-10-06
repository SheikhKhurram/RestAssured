package Testcases;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import TestBase.GettingSessionID;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;

import static io.restassured.RestAssured.given;
public class DeleteIssue 
{
	String bugID;
	String sessionKey;
  @BeforeMethod
  public void setUp() throws InterruptedException
  {
	 sessionKey = GettingSessionID.gettingSessionID();
	 GettingSessionID.initializing();
	 bugID  = CreatingBug.creatingBug();
	 Thread.sleep(5000); 
	 }
  
  @Test
  public void deleteBug()
  {
	  
	  RestAssured.baseURI = "http://localhost:8080";
	  
	   given().
	   contentType(ContentType.JSON).
	   headers("cookie" , sessionKey).
	   when().
	   delete("/rest/api/2/issue/"+bugID+"+").
	   then().assertThat().statusCode(204);
	  
  }
  
}
