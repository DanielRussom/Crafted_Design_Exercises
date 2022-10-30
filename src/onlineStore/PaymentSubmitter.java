package onlineStore;

public class PaymentSubmitter {

	private StockChecker stockChecker;
	private PaymentGateway paymentGateway;
	private EmailSender emailSender;

	public PaymentSubmitter(StockChecker stockChecker, PaymentGateway paymentGateway, EmailSender emailSender) {
		this.stockChecker = stockChecker;
		this.paymentGateway = paymentGateway;
		this.emailSender = emailSender;
	}

	public PaymentSubmitResult submit(StoreUser user) {
		var stockResult = stockChecker.checkStock(user.getBasket());
		
		if(stockResult.getOutOfStockItems() != "") {
			return new PaymentSubmitResult("Item(s) " + stockResult.getOutOfStockItems() + " is out of stock");
		}
		
		var gatewayResult = paymentGateway.sendPayment(user);
		if(gatewayResult.getMessage() != "") {
			return new PaymentSubmitResult(gatewayResult.getMessage());
		}
		
		emailSender.sendConfirmationEmail(user);
		return new PaymentSubmitResult(PaymentStatus.Success);
	}

}
