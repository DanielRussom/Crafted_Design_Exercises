package onlineStore;

public class PaymentSubmitter {

	private StockChecker stockChecker;

	public PaymentSubmitter(StockChecker stockChecker) {
		this.stockChecker = stockChecker;
	}

	public PaymentSubmitResult submit(StoreUser user) {
		stockChecker.checkStock(user.getBasket());
		return new PaymentSubmitResult("Item(s) test is out of stock");
	}

}
