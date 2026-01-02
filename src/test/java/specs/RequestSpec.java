package specs;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;
import utils.ConfigReader;

public class RequestSpec {
	
	public static RequestSpecification getRequestSpec() {
		return new RequestSpecBuilder().
		setBaseUri(ConfigReader.get("base.uri")).
		addHeader("Content-Type", "application/json").build();
	}

}
