import java.sql.SQLException;

public class clsCompany {
	int ID;
	String companyCode;
	double price;
	public clsCompany(int ID, String companyCode, double price) {
		this.ID = ID;
		this.companyCode = companyCode;
		this.price = price;
	}
	
	public clsCompany(int ID, String companyCode) {
		this.ID = ID;
		this.companyCode = companyCode;
	}
	
	public clsCompany() {
		
	}
	
	public int getID() {
		return this.ID;
	}
	
	public String getCompanyCode() {
		return this.companyCode;
	}
	
	public double getPrice() {
		return this.price;
	}
	public boolean Transaction(int customerID, String companyName, double amount, String date) {
		MicrosoftSQLConnection sql = new MicrosoftSQLConnection();
		try {
			//if(sql.CheckRight(Username, "5")) {
				System.out.println("After checkrights");
				sql.openConnection();
				System.out.println("After connection");
				System.out.println("Company name: " + companyName);
				clsResult cls = sql.getCurrentPrice(companyName, date);
				double price = cls.getPrice();
				System.out.println("Price: " + price);
				clsCompany company = sql.getCompanyID(companyName);
				System.out.println("Company name: " + companyName);
				System.out.println("CustomerID: " + customerID);
				System.out.println("Date: " + date);
				System.out.println("Amount: " + amount);
				if(price == 0) {
					return false;
				}
				sql.makeTransactions(customerID, company.getID(), price, amount, amount * price);
				sql.closeConnection();
				return true;
				//sql.makeTransactions(userID, companyID, price, amount, totalPrice);
			//}
			/*else {
				System.out.println("not");
			}*/
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			sql.closeConnection();
			return false;
		}
	}
	
}
