package onlineStore;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class PaymentSubmitterShould {

	@ParameterizedTest
	@ValueSource(strings = {"test", "mela,panino"})
	void list_out_of_stock_items_in_basket(String outOfStockItems) {
		var stockChecker = mock(StockChecker.class);
		var basket = new StoreBasket();
		var underTest = new PaymentSubmitter(stockChecker);
		var user = mock(StoreUser.class);
		when(user.getBasket()).thenReturn(basket);
		var expectedMessage = "Item(s) " + outOfStockItems + " is out of stock";

		var checkStockResult = mock(CheckStockResult.class);
		when(checkStockResult.getOutOfStockItems()).thenReturn(outOfStockItems);
		when(stockChecker.checkStock(basket)).thenReturn(checkStockResult);
		
		var submitResult = underTest.submit(user);

		assertEquals(expectedMessage, submitResult.getMessage());
		assertEquals(PaymentStatus.Fail, submitResult.getStatus());
		verify(stockChecker, times(1)).checkStock(basket);
	}
	
	@Test
	void log_payment_gateway_error_reason() {
		var stockChecker = mock(StockChecker.class);
		var paymentGateway = mock(PaymentGateway.class);
		var basket = new StoreBasket();
		var underTest = new PaymentSubmitter(stockChecker, paymentGateway);
		var user = mock(StoreUser.class);
		when(user.getBasket()).thenReturn(basket);
		var expectedMessage = "User failed credit check.";

		var checkStockResult = mock(CheckStockResult.class);
		when(checkStockResult.getOutOfStockItems()).thenReturn("");
		when(stockChecker.checkStock(basket)).thenReturn(checkStockResult);
		var paymentResult = mock(PaymentGatewayResult.class);
		when(paymentGateway.sendPayment(user)).thenReturn(paymentResult);
		
		var submitResult = underTest.submit(user);

		verify(paymentGateway, times(1)).sendPayment(user);
		assertEquals(expectedMessage, submitResult.getMessage());
		assertEquals(PaymentStatus.Fail, submitResult.getStatus());
	}

}
