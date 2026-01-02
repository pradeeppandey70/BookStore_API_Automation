package utils;

public class TestDataUtils {

	public static String randomUsername() {
		return "user_" + System.currentTimeMillis();
	}

	public static String validPassword() {
		return "Test@123";
	}

}
