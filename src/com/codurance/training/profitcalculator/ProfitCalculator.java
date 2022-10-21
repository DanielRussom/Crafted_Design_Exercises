package com.codurance.training.profitcalculator;

public final class ProfitCalculator {
    private ExchangeRates exchangeRates = new ExchangeRates();
    private final Currency localCurrency;
    private Money localAmount = new Money(0);
    private Money foreignAmount = new Money(0);

    public ProfitCalculator(Currency currency) {
        this.localCurrency = currency;
    }

	public void add(BankTransaction transaction) {
        var realAmount = exchangeRates.exchange(transaction, localCurrency);
        
        if (transaction.isOutgoing()) {
            realAmount.invertValue();
        }
        
        if (transaction.isLocal(localCurrency)) {
            localAmount.add(realAmount);
            return;
        }
        
        foreignAmount.add(realAmount);
    }

    public Money calculateProfit() {
    	return localAmount.calculateProfit(foreignAmount);
    }

    public Money calculateTax() {
    	return localAmount.calculateTax();
    }
}
