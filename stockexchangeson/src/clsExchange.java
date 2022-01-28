
public class clsExchange {
	String userName;
	String companyDesc;
	double price;
	double amount;
	double total;
	String createdDate;
	
	public clsExchange(String userName, String companyDesc, double price, double amount, double total, String createdDate){
		this.userName = userName;
		this.companyDesc = companyDesc;
		this.price = price;
		this.amount = amount;
		this.total = total;
		this.createdDate = createdDate;
	}

	public String getUserName() {
		return this.userName;
	}
	
	public String getCompanyDesc() {
		return this.companyDesc;
	}
	
	public double getPrice() {
		return this.price;
	}
	
	public double getAmount() {
		return this.amount;
	}
	
	public double getTotal() {
		return this.total;
	}
	
	public String getCreatedDate() {
		return this.createdDate;
	}
}
