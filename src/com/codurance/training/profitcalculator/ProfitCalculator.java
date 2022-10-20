package com.codurance.training.profitcalculator;

import java.util.Map;

public final class ProfitCalculator {
    private static final Map<Currency, Double> EXCHANGE_RATES = Map.of(
    		Currency.GBP, 1.0, 
    		Currency.USD, 1.6,
    		Currency.EUR, 1.2);

    private final Currency localCurrency;
    private int localAmount = 0;
    private int foreignAmount = 0;

    public ProfitCalculator(Currency currency) {
        this.localCurrency = currency;
        Double exchangeRate = EXCHANGE_RATES.get(currency);
        if (exchangeRate == null) {
            throw new IllegalArgumentException("Invalid currency.");
        }
    }

	public void add(BankTransaction transaction) {
        int realAmount = transaction.getAmount();
        Double exchangeRate = EXCHANGE_RATES.get(transaction.getCurrency()) / EXCHANGE_RATES.get(localCurrency);
        
        if (exchangeRate != null) {
            realAmount /= exchangeRate;
        }
        
        if (transaction.isOutgoing()) {
            realAmount = -realAmount;
        }
        
        if (localCurrency.equals(transaction.getCurrency())) {
            this.localAmount += realAmount;
        } else {
            this.foreignAmount += realAmount;
        }
    }

    public Amount calculateProfit() {
        var profit = localAmount - calculateTax() + foreignAmount;
        return new Amount(profit);
    }

    public int calculateTax() {
        if (localAmount < 0) {
            return 0;
        }

        return (int) (localAmount * 0.2);
    }
}
