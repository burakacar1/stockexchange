import java.util.ArrayList;

public class clsResult {
	boolean result;
	String message;
	double price;
	//ArrayList<clsExchange> ExchangesLogs;
	
	
	public clsResult(boolean result, String message) {
		this.result = result;
		this.message = message;
		//ExchangesLogs=new ArrayList<clsExchange>();
	} 
	
	public clsResult(boolean result, String message, double price) {
		this.result = result;
		this.message = message;
		this.price = price;
		//ExchangesLogs=new ArrayList<clsExchange>();		
	}
	
	//public void _AddNewExchangeLog(clsExchange a)
	
	{
		//this.ExchangesLogs.Add(a);
		
	}
	
	
	public boolean getResult() {
		return this.result;
	}
	
	public String getMessage() {
		return this.message;
	}
	
	public double getPrice() {
		return this.price;
	}
	
}

