package com.codurance.training.profitcalculator;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

public final class ProfitCalculatorTest {
	private final ProfitCalculator gbpCalculator = new ProfitCalculator(Currency.GBP);
	private final ProfitCalculator eurCalculator = new ProfitCalculator(Currency.EUR);

	@Test
	public void calculates_the_tax_at_20_percent() {
		gbpCalculator.add(new BankTransaction(500, Currency.GBP, true));

		var profit = gbpCalculator.calculateProfit();
		int tax = gbpCalculator.calculateTax();

		assertEquals(400, profit.Value);
		assertEquals(100, tax);
	}

	@Test
	public void calculates_the_tax_of_multiple_amounts() {
		gbpCalculator.add(new BankTransaction(120, Currency.GBP, true));
		gbpCalculator.add(new BankTransaction(200, Currency.GBP, true));

		var profit = gbpCalculator.calculateProfit();
		int tax = gbpCalculator.calculateTax();

		assertEquals(256, profit.Value);
		assertEquals(64, tax);
	}

	@Test
	public void different_currencies_are_not_taxed() {
		gbpCalculator.add(new BankTransaction(120, Currency.GBP, true));
		gbpCalculator.add(new BankTransaction(200, Currency.USD, true));

		var profit = gbpCalculator.calculateProfit();
		int tax = gbpCalculator.calculateTax();

		assertEquals(221, profit.Value);
		assertEquals(24, tax);
	}

	@Test
	public void handle_outgoings() {
		gbpCalculator.add(new BankTransaction(500, Currency.GBP, true));
		gbpCalculator.add(new BankTransaction(80, Currency.USD, true));
		gbpCalculator.add(new BankTransaction(360, Currency.EUR, false));

		var profit = gbpCalculator.calculateProfit();
		int tax = gbpCalculator.calculateTax();

		assertEquals(150, profit.Value);
		assertEquals(100, tax);
	}

	@Test
	public void a_negative_balance_results_in_no_tax() {
		gbpCalculator.add(new BankTransaction(500, Currency.GBP, true));
		gbpCalculator.add(new BankTransaction(200, Currency.GBP, false));
		gbpCalculator.add(new BankTransaction(400, Currency.GBP, false));
		gbpCalculator.add(new BankTransaction(20, Currency.GBP, false));

		var profit = gbpCalculator.calculateProfit();
		int tax = gbpCalculator.calculateTax();

		assertEquals(-120, profit.Value);
		assertEquals(0, tax);
	}

	@Test
	public void everything_is_reported_in_the_local_currency() {
		eurCalculator.add(new BankTransaction(400, Currency.GBP, true));
		eurCalculator.add(new BankTransaction(200, Currency.USD, false));
		eurCalculator.add(new BankTransaction(200, Currency.EUR, true));

		var profit = eurCalculator.calculateProfit();
		int tax = eurCalculator.calculateTax();

		assertEquals(491, profit.Value);
		assertEquals(40, tax);
	}

}
