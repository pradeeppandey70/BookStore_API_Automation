package utils;

import io.restassured.RestAssured;
import specs.RequestSpec;

public class BookUtils{
	
	 public static String getFirstBookIsbn() {
	        return RestAssured.given()
	                .spec(RequestSpec.getRequestSpec())
	        .when()
	                .get("/BookStore/v1/Books")
	        .then()
	                .statusCode(200)
	                .extract()
	                .path("books[0].isbn");
	    }

}
