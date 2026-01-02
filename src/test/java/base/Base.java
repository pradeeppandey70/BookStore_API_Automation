package base;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;

import io.restassured.RestAssured;
import utils.ConfigReader;

public class Base {
	
	@BeforeSuite
	public void beforeSuiteSetup() {
		RestAssured.baseURI = ConfigReader.get("base.uri");
	}
	
	@BeforeClass
	public void beforeClassSetup() {
		RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
	}

}
