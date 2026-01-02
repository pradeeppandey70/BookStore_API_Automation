package tests;

import java.util.List;
import org.testng.Assert;
import org.testng.annotations.Test;
import base.Base;
import io.restassured.RestAssured;
import payloads.AddBookRequest;
import payloads.Book;
import payloads.DeleteBookRequest;
import specs.RequestSpec;
import utils.BookUtils;
import utils.TestDataUtils;
import utils.TokenManager;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.Matchers.*;

public class BookStoreTests extends Base{
	@Test(
		    groups = {"regression"},
		    description = "Add book to user and delete it successfully"
	)
    public void addAndDeleteBook_forUser() {

        // 1️⃣ Create user
        String username = TestDataUtils.randomUsername();
        String password = TestDataUtils.validPassword();

        String userId =
            RestAssured.given()
                .spec(RequestSpec.getRequestSpec())
                .body(new payloads.CreateUserRequest(username, password))
            .when()
                .post("/Account/v1/User")
            .then()
                .statusCode(201)
                .extract()
                .path("userID");

        // 2️⃣ Generate token for same user
        String token = TokenManager.getToken(username, password);
        Assert.assertNotNull(token);

        // 3️⃣ Get a valid book ISBN
        String isbn = BookUtils.getFirstBookIsbn();

        // 4️⃣ Add book
        AddBookRequest addBookRequest =
            new AddBookRequest(userId, List.of(new Book(isbn)));

        RestAssured.given()
            .spec(RequestSpec.getRequestSpec())
            .header("Authorization", "Bearer " + token)
            .body(addBookRequest)
        .when()
            .post("/BookStore/v1/Books")
        .then()
            .statusCode(201);

        // 5️⃣ Verify book added
        RestAssured.given()
            .spec(RequestSpec.getRequestSpec())
            .header("Authorization", "Bearer " + token)
        .when()
            .get("/Account/v1/User/" + userId)
        .then()
            .statusCode(200)
            .body("books.isbn",hasItem(isbn))
            .body(matchesJsonSchemaInClasspath("schemas/getBooksSchema.json"));

        // 6️⃣ Delete book
        DeleteBookRequest deleteRequest =
                new DeleteBookRequest(isbn, userId);
        RestAssured.given()
            .spec(RequestSpec.getRequestSpec())
            .header("Authorization", "Bearer " + token)
            .body(deleteRequest)
        .when()
            .delete("/BookStore/v1/Book")
        .then()
            .statusCode(204);
        	
    }
}
	
	


