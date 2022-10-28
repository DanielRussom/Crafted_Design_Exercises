package onlineStore;

public class CheckStockResult {

	private String outOfStockItems;
	private boolean isStockValid;

	public CheckStockResult(boolean isStockValid, String outOfStockItems) {
		this.isStockValid = isStockValid;
		this.outOfStockItems = outOfStockItems;
	}

}
