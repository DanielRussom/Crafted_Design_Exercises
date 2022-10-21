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
        int realAmount = transaction.getAmount();
        
        Double exchangeRate = EXCHANGE_RATES.getConversionRate(transaction.getCurrency(), localCurrency);
        
        if (exchangeRate != null) {
            realAmount /= exchangeRate;
        }
        
        if (transaction.isOutgoing()) {
            realAmount = -realAmount;
        }
        
        if (localCurrency.equals(transaction.getCurrency())) {
            localAmount.add(realAmount);
        } else {
            foreignAmount.add(realAmount);
        }
    }

    public Money calculateProfit() {
    	return localAmount.calculateProfit(foreignAmount.Value);
    }

    public Money calculateTax() {
    	return localAmount.calculateTax();
    }
}
