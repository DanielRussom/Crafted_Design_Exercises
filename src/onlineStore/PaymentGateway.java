package onlineStore;

public interface PaymentGateway {

	public PaymentGatewayResult sendPayment(StoreUser user);

}
