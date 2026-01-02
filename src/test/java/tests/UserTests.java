package tests;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import io.qameta.allure.*;
import org.testng.Assert;
import org.testng.annotations.Test;

import base.Base;
import io.restassured.RestAssured;
import payloads.CreateUserRequest;
import payloads.CreateUserResponse;
import specs.RequestSpec;
import utils.DataBaseUtils;
import utils.TestDataUtils;
import utils.TokenManager;

public class UserTests extends Base{
	
	@Test(
		groups = {"smoke", "regression"},
		description = "Create user and validate user details"
		)
	@Severity(SeverityLevel.CRITICAL)
    @Story("Create user → Generate token → Get user")
	public void createUser_and_getUserDetails() {
		String userName = TestDataUtils.randomUsername();
		String password = TestDataUtils.validPassword();
		CreateUserRequest request = new CreateUserRequest(userName,password);
		
		CreateUserResponse response =
				RestAssured.given().
				spec(RequestSpec.getRequestSpec()).
				body(request).
				when().post("/Account/v1/User").
				then().statusCode(201).body(matchesJsonSchemaInClasspath("schemas/createUserSchema.json")).
				extract().as(CreateUserResponse.class);
		
		String userId = response.getUserID();
		String token = TokenManager.getToken(userName, password);
		System.out.println(token);
		Assert.assertNotNull(userId, "UserID should not be null");
		
		DataBaseUtils.insertUser(userId, userName);

		// DB validation
		String dbUsername = DataBaseUtils.getUsernameByUserId(userId);
		Assert.assertEquals(dbUsername, userName, "DB validation failed");
		
		RestAssured.given().
		spec(RequestSpec.getRequestSpec()).
		header("Authorization", "Bearer " + token).
		when().
		get("/Account/v1/User/" + userId).
		then().
		statusCode(200).body(matchesJsonSchemaInClasspath("schemas/getUserSchema.json"));
	}

}
