package utils;

import io.restassured.RestAssured;
import payloads.LoginRequest;
import payloads.TokenResponse;
import specs.RequestSpec;

public class TokenManager {
	
	private TokenManager() {
		
	}
	
	public static String getToken(String userName, String password) {
		
		LoginRequest login = new LoginRequest(userName, password);
		TokenResponse response =
			RestAssured.given().
			spec(RequestSpec.getRequestSpec()).
			body(login).
			when().
			post("/Account/v1/GenerateToken").
			then().
			extract().
			as(TokenResponse.class);
		return response.getToken();
	}

}
