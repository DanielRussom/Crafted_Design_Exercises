package onlineStore;

public class PaymentSubmitter {

	private StockChecker stockChecker;
	private PaymentGateway paymentGateway;

	public PaymentSubmitter(StockChecker stockChecker) {
		this.stockChecker = stockChecker;
	}

	public PaymentSubmitter(StockChecker stockChecker, PaymentGateway paymentGateway) {
		this.stockChecker = stockChecker;
		this.paymentGateway = paymentGateway;
	}

	public PaymentSubmitResult submit(StoreUser user) {
		var stockResult = stockChecker.checkStock(user.getBasket());
		
		if(stockResult.getOutOfStockItems() == "") {
			paymentGateway.sendPayment(user);
			return new PaymentSubmitResult("User failed credit check.");
		}
		return new PaymentSubmitResult("Item(s) " + stockResult.getOutOfStockItems() + " is out of stock");
	}

}
