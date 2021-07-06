package testCases;

import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import static io.restassured.RestAssured.given;

import java.io.File;
import java.util.concurrent.TimeUnit;

import org.apache.commons.logging.Log;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class CreateProduct {

	@Test
	public void update_A_Products() {
//		https://techfios.com/api-prod/api/product/
		// read.php
		
		String payload = ".\\src\\test\\java\\Json\\payload.json";
		
		Response response = 
				given()
				.log().all()
				.baseUri("https://techfios.com/api-prod/api/product")
				.header("Content-Type", "application/json; charset=UTF-8")
			//	.param("id", "1685")
				.body(new File(payload))
				.when().get("/read.php").then().extract()
				.response();

		int statuscode = response.getStatusCode();
		System.out.println("Status Code" + statuscode);
//		AssertJUnit.assertEquals(statuscode, 200);

long responseTime=		response.getTimeIn(TimeUnit.MILLISECONDS);

//Assert.assertEquals(responseTime, 200);
System.out.println("Response Time " + responseTime);
if(responseTime <= 2000) {System.out.println("Response Time is within Range");}
else {System.out.println("Out of Range");}

		String responseBody = response.getBody().asString();
	JsonPath jp  = new JsonPath(responseBody);
	
	String productName = jp.getString("name");
	System.out.println(productName);
	//AssertJUnit.assertEquals(productName, "iPhone 13.0" );
		
	String description = jp.getString("description");
	System.out.println(description);
	//AssertJUnit.assertEquals(description, "The super phone" );
	
	String price = jp.getString("price");
	System.out.println(price);
//	AssertJUnit.assertEquals(price, "199" );
	
	
	}

}
