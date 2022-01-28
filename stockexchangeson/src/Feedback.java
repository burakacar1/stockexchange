
public class Feedback {
	
	public Feedback(String userName, String comment) {
		this.userName = userName;
		this.comment = comment;
	}
	
	private String userName;
	
	private String comment;
	
	String viewFeedback() {
		return comment;
	}
	
	String viewName() {
		return userName;
	}
	
	void changeFeedback(String comment) {
		this.comment = comment;
	}
	
}
