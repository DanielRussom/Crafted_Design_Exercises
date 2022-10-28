package onlineStore;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.Test;

class PaymentSubmitterShould {

	@Test
	void not_process_an_empty_basket() {
		var stockChecker = mock(StockChecker.class);
		var basket = new StoreBasket();
		var underTest = new PaymentSubmitter();
		var user = new StoreUser();
		var expectedMessage = "Basket is empty";
		
		var submitResult = underTest.submit(user);

		assertEquals(expectedMessage, submitResult.getMessage());
		verify(stockChecker, times(0)).checkStock(basket);
	}

}
