package utils;

import java.io.InputStream;
import java.util.Properties;

public class ConfigReader {
	static Properties prop;
	static {
		try {
			prop = new Properties();
			InputStream is = ConfigReader.class.getClassLoader().getResourceAsStream("config.properties");
			prop.load(is);
		} catch (Exception e) {
			throw new RuntimeException("Failed to load config.properties");
		}
	}
	
	public static String get(String key) {
		return prop.getProperty(key);
	}

}
