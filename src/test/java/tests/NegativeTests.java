package tests;

import org.testng.annotations.Test;

import base.Base;
import io.restassured.RestAssured;
import payloads.AddBookRequest;
import payloads.Book;
import payloads.CreateUserRequest;
import specs.RequestSpec;
import utils.BookUtils;
import utils.TestDataUtils;
import utils.TokenManager;

public class NegativeTests extends Base{
	
	@Test(
		    groups = {"negative"},
		    description = "Verify duplicate user creation is not allowed"
		    )
	public void getBooks_withoutToken_shouldFail() {
		RestAssured.given().
		spec(RequestSpec.getRequestSpec()).
		when().get("/BookStore/v1/Books").
		then().statusCode(401);
	}
	
	@Test(
		    groups = {"negative"},
		    description = "Verify duplicate user creation is not allowed"
		    )
	public void createDuplicateUser_shouldFail() {
		
		String username = TestDataUtils.randomUsername();
	    String password = TestDataUtils.validPassword();

	    CreateUserRequest request =
	            new CreateUserRequest(username, password);
		RestAssured.given().
							spec(RequestSpec.getRequestSpec()).body(request).
					when().
							post("/Account/v1/User").
					then().
							statusCode(201);
		
		RestAssured.given().
							spec(RequestSpec.getRequestSpec()).body(request).
					when().
							post("/Account/v1/User").
					then().
							statusCode(406).
							body("message", org.hamcrest.Matchers.containsString("exists"));
	}
	
	
	@Test(
		    groups = {"negative"},
		    description = "Verify duplicate user creation is not allowed"
		    )
	public void addBook_withoutToken_shouldFail() {

	    String isbn = BookUtils.getFirstBookIsbn();

	    AddBookRequest addBook =
	            new AddBookRequest("dummyUserId",
	                    java.util.List.of(new Book(isbn)));

	    RestAssured.given()
	        .spec(RequestSpec.getRequestSpec())
	        .body(addBook)
	    .when()
	        .post("/BookStore/v1/Books")
	    .then()
	        .statusCode(401);
	}
	
	@Test(
		    groups = {"negative"},
		    description = "Verify duplicate user creation is not allowed"
		    )
	public void deleteBook_ofAnotherUser_shouldFail() {

	    // User A
	    String userA = TestDataUtils.randomUsername();
	    String password = TestDataUtils.validPassword();

	    String userAId =
	        RestAssured.given()
	            .spec(RequestSpec.getRequestSpec())
	            .body(new CreateUserRequest(userA, password))
	        .when()
	            .post("/Account/v1/User")
	        .then()
	            .extract().path("userID");

	    String tokenA = TokenManager.getToken(userA, password);

	    String isbn = BookUtils.getFirstBookIsbn();
	    
	    
	    

	    // Add book to User A
	    RestAssured.given()
	        .spec(RequestSpec.getRequestSpec())
	        .header("Authorization", "Bearer " + tokenA)
	        .body(new AddBookRequest(userAId,
	                java.util.List.of(new Book(isbn))))
	    .when()
	        .post("/BookStore/v1/Books")
	    .then()
	        .statusCode(201);

	    // User B tries to delete
	    String userB = TestDataUtils.randomUsername();
	    String userBId =
	        RestAssured.given()
	            .spec(RequestSpec.getRequestSpec())
	            .body(new CreateUserRequest(userB, password))
	        .when()
	            .post("/Account/v1/User")
	        .then()
	            .extract().path("userID");

	    String tokenB = TokenManager.getToken(userB, password);

	    RestAssured.given()
	        .spec(RequestSpec.getRequestSpec())
	        .header("Authorization", "Bearer " + tokenB)
	        .body(new payloads.DeleteBookRequest(isbn, userAId))
	    .when()
	        .delete("/BookStore/v1/Book")
	    .then()
	        .statusCode(403);
	}
	
	



}
