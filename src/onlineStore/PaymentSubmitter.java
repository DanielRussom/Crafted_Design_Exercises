package onlineStore;

public class PaymentSubmitter {

	private StockChecker stockChecker;

	public PaymentSubmitter(StockChecker stockChecker) {
		this.stockChecker = stockChecker;
	}

	public PaymentSubmitResult submit(StoreUser user) {
		var stockResult = stockChecker.checkStock(user.getBasket());
		return new PaymentSubmitResult("Item(s) " + stockResult.getOutOfStockItems() + " is out of stock");
	}

}
