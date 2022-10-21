package com.codurance.training.profitcalculator;

public final class ProfitCalculator {
    private static final ExchangeRates EXCHANGE_RATES = new ExchangeRates();

    private final Currency localCurrency;
    private Money localAmount = new Money(0);
    private Money foreignAmount = new Money(0);

    public ProfitCalculator(Currency currency) {
        this.localCurrency = currency;
        Double exchangeRate = EXCHANGE_RATES.get(currency);
        if (exchangeRate == null) {
            throw new IllegalArgumentException("Invalid currency.");
        }
    }

	public void add(BankTransaction transaction) {
        var realAmount = EXCHANGE_RATES.exchange(transaction, localCurrency);
        
        if (transaction.isOutgoing()) {
            realAmount.Value = -realAmount.Value;
        }
        
        if (transaction.isLocal(localCurrency)) {
            localAmount.add(realAmount);
        } else {
            foreignAmount.add(realAmount);
        }
    }

    public Money calculateProfit() {
    	return localAmount.calculateProfit(foreignAmount);
    }

    public Money calculateTax() {
    	return localAmount.calculateTax();
    }
}
