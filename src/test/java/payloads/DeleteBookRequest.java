package payloads;

public class DeleteBookRequest {
	private String isbn;
    private String userId;

    public DeleteBookRequest() {}

    public DeleteBookRequest(String isbn, String userId) {
        this.isbn = isbn;
        this.userId = userId;
    }

    public String getIsbn() {
        return isbn;
    }

    public String getUserId() {
        return userId;
    }

	
	
}
