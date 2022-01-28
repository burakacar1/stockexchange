
public class clsUser {
	String username;
	String password;
	String description;
	
	public clsUser(String username, String password, String description) {
		this.username = username;
		this.password = password;
		this.description = description;
	}
	
	public String getUserName() {
		return this.username;
	}
	
	public String getPassword() {
		return this.password;
	}
	
	public String getDescrýptýon() {
		return this.description;
	}
}
