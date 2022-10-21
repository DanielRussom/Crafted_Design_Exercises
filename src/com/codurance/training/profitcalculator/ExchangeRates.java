package com.codurance.training.profitcalculator;

import java.util.Map;

public class ExchangeRates {
	Map<Currency, Double> EXCHANGE_RATES = Map.of(
    		Currency.GBP, 1.0, 
    		Currency.USD, 1.6,
    		Currency.EUR, 1.2);

	public Double get(Currency currency) {
		return EXCHANGE_RATES.get(currency);
	}

	public double getConversionRate(Currency fromCurrency, Currency toCurrency) {
		return EXCHANGE_RATES.get(fromCurrency) / EXCHANGE_RATES.get(toCurrency);
	}
	
	
}
