
public class Register extends UserInfo{
	
	public Register(String userName, char[] userPassword, int memberType) {
		this.userName = userName;
		this.userPassword = userPassword;
		this.memberType = memberType;
	}
	
	private String userName;
	private char[] userPassword;
	private int memberType;
	
	String password = String.valueOf(userPassword);
	
	void setUserName(String userName) {
		this.userName = userName;
	}
	
	void setPassword(char[] userPassword) {
		this.userPassword = userPassword;
	}
	
	void setMemberType(int memberType) {
		this.memberType = memberType;
	}
	String getUserName() {
		return userName;
	}
	
	String getPassword() {
		return password;
	}
	
	String valueOfMember() {
		if (memberType == 1) {
			return "Customer";
		}
		else if (memberType == 2) {
			return "Broker";
		}
		return "";
	}
}
