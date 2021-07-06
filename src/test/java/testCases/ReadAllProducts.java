package testCases;

import static io.restassured.RestAssured.given;

import org.testng.Assert;
import org.testng.AssertJUnit;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import io.restassured.response.Response;

public class ReadAllProducts {

	@Test
	public void All_Products() {
//		https://techfios.com/api-prod/api/product/
		// read.php
		
		
		SoftAssert softassert = new SoftAssert();
		
		Response response = given().baseUri("https://techfios.com/api-prod/api/product")
				.header("Content-Type", "application/json; charset=UTF-8")
				.queryParam("id", "1685")
				.when().get("/read.php")
				.then().extract()
				.response();

		int statuscode = response.getStatusCode();
		System.out.println("Status Code" + statuscode);
		Assert.assertEquals(statuscode, 200);

		String responseBody = response.getBody().prettyPrint();
		
		// System.out.println("Response " + response.asString());

	}

}
