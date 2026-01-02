package payloads;

public class CreateUserRequest {
	
	private String username;
	
	private String password;

	public CreateUserRequest() {

	}

	public CreateUserRequest(String username, String password) {
		this.username = username;
		this.password = password;
	}

	public String getUserName() {
		return username;
	}

	public String getPassword() {
		return password;
	}

}
