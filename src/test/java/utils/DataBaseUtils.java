package utils;

import java.util.HashMap;
import java.util.Map;

public class DataBaseUtils {
	 // Simulated DB table
    private static final Map<String, String> USER_TABLE = new HashMap<>();

    public static void insertUser(String userId, String username) {
        USER_TABLE.put(userId, username);
    }

    public static String getUsernameByUserId(String userId) {
        return USER_TABLE.get(userId);
    }

}
