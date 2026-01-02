package tests;

import static org.hamcrest.Matchers.*;

import org.testng.annotations.Test;

import base.Base;
import io.restassured.RestAssured;
import specs.RequestSpec;
import utils.TokenManager;

public class AuthSmokeTest extends Base {
	@Test
	public void authorisedUser_canAccessBooks() {
		RestAssured.given().
		spec(RequestSpec.getRequestSpec()).
		header("Authorization", "Bearer " + TokenManager.getToken(null, null)).
		when().get("/BookStore/v1/Books").
		then().statusCode(200).
		body("books.size()", greaterThan(0));
	}

}
