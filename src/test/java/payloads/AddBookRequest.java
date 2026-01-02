package payloads;

import java.util.List;

public class AddBookRequest {
	private String userId;
	private List<Book> collectionOfIsbns;
	
	public AddBookRequest() {}

    public AddBookRequest(String userId, List<Book> collectionOfIsbns) {
        this.userId = userId;
        this.collectionOfIsbns = collectionOfIsbns;
    }

    public String getUserId() {
        return userId;
    }

    public List<Book> getCollectionOfIsbns() {
        return collectionOfIsbns;
    }

}
