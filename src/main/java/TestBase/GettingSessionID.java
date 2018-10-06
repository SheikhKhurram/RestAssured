package TestBase;

import org.apache.http.entity.ContentType;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import static io.restassured.RestAssured.given;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class GettingSessionID
{
	public static FileInputStream file = null;
	public static Properties prop;
	
    

	public static String gettingSessionID()      
      {
    	 RestAssured.baseURI = "http://localhost:8080";
    	  Response response = 
    			  given().
    			  headers("Content-Type" , "application/json").  
    			  body("{ \"username\": \"sheik.kurram93\", \"password\": \"123456\" }").
    			  when().
    			  post("/rest/auth/1/session").
    			  then().
    			  assertThat().
    			  statusCode(200).
    			  extract().response();
    	  
    	  JsonPath json = response.jsonPath();
    	  
    	 String sessionName = json.get("session.name");
    	  
    	 String sessionValue = json.get("session.value");
    	
    	 return sessionName+"="+sessionValue;
      }
	
    public static void initializing()
    {
    
    	try
    	{
    	 file = new FileInputStream("C:\\Users\\khurram\\eclipse-workspace\\JIraRestAPI\\src\\test\\java\\Config.properties");
    	}
    	catch (FileNotFoundException e)
    	{
    		e.printStackTrace();
    	}
         prop = new Properties();
         try {
			prop.load(file);
		} catch (IOException e) {
			e.printStackTrace();
		}
         
    }
}
