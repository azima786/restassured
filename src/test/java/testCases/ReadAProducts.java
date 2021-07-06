package testCases;

import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import static io.restassured.RestAssured.given;

import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class ReadAProducts {

	@Test
	public void CreateProduct() {
//		https://techfios.com/api-prod/api/product/
		// read.php
		/*{
		    "name" : "iPhone 13.0",
		    "price" : "199",
		    "description" : "The best phone.",
		    "category_id" : 2
		}*/
		
		HashMap<String, String> payload = new HashMap<String, String>();
		payload.put("name", "Fundamentals for QA people");
		payload.put("price", "149");
		payload.put("description", "Must buy");
		payload.put("category_id", "6");
		
		Response response = 
				given()
				.baseUri("https://techfios.com/api-prod/api/product")
				.header("Content-Type", "application/json;")
				.body(payload)
				.when().post("/create.php")
				.then().extract()
				.response();

		int statuscode = response.getStatusCode();
		System.out.println("Status Code" + statuscode);
		Assert.assertEquals(statuscode, 201);

long responseTime=		response.getTimeIn(TimeUnit.MILLISECONDS);

Assert.assertEquals(responseTime, 201);
System.out.println("Response Time " + responseTime);
if(responseTime <= 2000) {System.out.println("Response Time is within Range");}
else {System.out.println("Out of Range");}

		String responseBody = response.getBody().asString();
	JsonPath jp  = new JsonPath(responseBody);
	
	String message = jp.getString("message");
	System.out.println(message);
	Assert.assertEquals(message, "Product was created" );
		
	
	
	
	}

}
