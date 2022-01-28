public class Stocks {
	String companyName;
	double exchangePrice;
	public Stocks(String companyName, double exchangePrice) {
		this.companyName = companyName;
		this.exchangePrice = exchangePrice;
	}
	public String getCompanyName() {
		return companyName;
	}
	public double getExchangePrice() {
		return exchangePrice;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	public void setExchangePrice(double exchangePrice) {
		this.exchangePrice = exchangePrice;
	}
}
