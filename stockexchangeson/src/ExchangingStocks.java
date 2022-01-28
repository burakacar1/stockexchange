
public class ExchangingStocks extends Stocks{
	public String companyName;
	double exhangePrice;
	public ExchangingStocks(String companyName, double exchangePrice) {
		super(companyName, exchangePrice);
	}
	public double totalPrice(int amount) {
		return amount * exchangePrice;
	}
}
