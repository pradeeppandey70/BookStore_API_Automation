package tests;

import static org.hamcrest.Matchers.greaterThan;

import org.testng.annotations.Test;

import base.Base;
import io.restassured.RestAssured;
import specs.RequestSpec;

public class SmokeTest extends Base{
	
	@Test
	public void getAllBooks_shouldReturn200() {
		RestAssured.given().
		spec(RequestSpec.getRequestSpec()).
		when().get("/BookStore/v1/Books").
		then().statusCode(200).
		body("books.size()", greaterThan(0) );
	}

}
