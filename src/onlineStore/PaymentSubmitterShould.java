package onlineStore;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;

class PaymentSubmitterShould {

	@Test
	void list_out_of_stock_items_in_basket() {
		var stockChecker = mock(StockChecker.class);
		var basket = new StoreBasket();
		var underTest = new PaymentSubmitter(stockChecker);
		var user = mock(StoreUser.class);
		when(user.getBasket()).thenReturn(basket);
		var expectedMessage = "Item(s) test is out of stock";

		var checkStockResult = new CheckStockResult(false, "test");
		when(stockChecker.checkStock(basket)).thenReturn(checkStockResult);
		
		var submitResult = underTest.submit(user);

		assertEquals(expectedMessage, submitResult.getMessage());
		verify(stockChecker, times(1)).checkStock(basket);
	}

}
