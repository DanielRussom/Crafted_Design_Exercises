package onlineStore;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class PaymentSubmitterShould {
	
	private PaymentSubmitter underTest;
	private StockChecker stockChecker;
	private PaymentGateway paymentGateway;
	private StoreUser user;

	@BeforeEach
	void initialise() {
		stockChecker = mock(StockChecker.class);
		paymentGateway = mock(PaymentGateway.class);
		underTest = new PaymentSubmitter(stockChecker, paymentGateway);
		
		user = mock(StoreUser.class);
		when(user.getBasket()).thenReturn(new StoreBasket());
	}

	@ParameterizedTest
	@ValueSource(strings = {"test", "mela,panino"})
	void list_out_of_stock_items_in_basket(String outOfStockItems) {
		var expectedMessage = "Item(s) " + outOfStockItems + " is out of stock";

		var checkStockResult = mock(CheckStockResult.class);
		when(checkStockResult.getOutOfStockItems()).thenReturn(outOfStockItems);
		when(stockChecker.checkStock(any(StoreBasket.class))).thenReturn(checkStockResult);
		
		var submitResult = underTest.submit(user);

		assertEquals(expectedMessage, submitResult.getMessage());
		assertEquals(PaymentStatus.Fail, submitResult.getStatus());
		verify(stockChecker, times(1)).checkStock(any(StoreBasket.class));
		verify(paymentGateway, times(0)).sendPayment(user);
	}
	
	@Test
	void log_payment_gateway_error_reason() {
		var expectedMessage = "User failed credit check.";
		
		var checkStockResult = mock(CheckStockResult.class);
		when(checkStockResult.getOutOfStockItems()).thenReturn("");
		when(stockChecker.checkStock(any(StoreBasket.class))).thenReturn(checkStockResult);
		
		var paymentResult = mock(PaymentGatewayResult.class);
		when(paymentGateway.sendPayment(user)).thenReturn(paymentResult);
		
		var submitResult = underTest.submit(user);

		assertEquals(expectedMessage, submitResult.getMessage());
		assertEquals(PaymentStatus.Fail, submitResult.getStatus());
		verify(paymentGateway, times(1)).sendPayment(user);
	}

}
