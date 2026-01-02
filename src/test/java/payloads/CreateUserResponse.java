package payloads;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
@JsonIgnoreProperties(ignoreUnknown = true)
public class CreateUserResponse {
	private String userID;
	private String userName;
	private List<Object> books;
	
	public String getUserID() {
		return userID;
	}
	
	public String getUserName() {
		return userName;
	}
}
