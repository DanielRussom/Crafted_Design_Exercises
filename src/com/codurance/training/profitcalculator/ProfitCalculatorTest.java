package com.codurance.training.profitcalculator;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

public final class ProfitCalculatorTest {
	private final ProfitCalculator gbpCalculator = new ProfitCalculator("GBP");
	private final ProfitCalculator eurCalculator = new ProfitCalculator("EUR");

	@Test
	public void calculates_the_tax_at_20_percent() {
		gbpCalculator.add(new BankTransaction(500, "GBP", true));

		int profit = gbpCalculator.calculateProfit();
		int tax = gbpCalculator.calculateTax();

		assertEquals(400, profit);
		assertEquals(100, tax);
	}

	@Test
	public void calculates_the_tax_of_multiple_amounts() {
		gbpCalculator.add(new BankTransaction(120, "GBP", true));
		gbpCalculator.add(new BankTransaction(200, "GBP", true));

		int profit = gbpCalculator.calculateProfit();
		int tax = gbpCalculator.calculateTax();

		assertEquals(256, profit);
		assertEquals(64, tax);
	}

	@Test
	public void different_currencies_are_not_taxed() {
		gbpCalculator.add(new BankTransaction(120, "GBP", true));
		gbpCalculator.add(new BankTransaction(200, "USD", true));

		int profit = gbpCalculator.calculateProfit();
		int tax = gbpCalculator.calculateTax();

		assertEquals(221, profit);
		assertEquals(24, tax);
	}

	@Test
	public void handle_outgoings() {
		gbpCalculator.add(new BankTransaction(500, "GBP", true));
		gbpCalculator.add(new BankTransaction(80, "USD", true));
		gbpCalculator.add(new BankTransaction(360, "EUR", false));

		int profit = gbpCalculator.calculateProfit();
		int tax = gbpCalculator.calculateTax();

		assertEquals(150, profit);
		assertEquals(100, tax);
	}

	@Test
	public void a_negative_balance_results_in_no_tax() {
		gbpCalculator.add(new BankTransaction(500, "GBP", true));
		gbpCalculator.add(new BankTransaction(200, "GBP", false));
		gbpCalculator.add(new BankTransaction(400, "GBP", false));
		gbpCalculator.add(new BankTransaction(20, "GBP", false));

		int profit = gbpCalculator.calculateProfit();
		int tax = gbpCalculator.calculateTax();

		assertEquals(-120, profit);
		assertEquals(0, tax);
	}

	@Test
	public void everything_is_reported_in_the_local_currency() {
		eurCalculator.add(new BankTransaction(400, "GBP", true));
		eurCalculator.add(new BankTransaction(200, "USD", false));
		eurCalculator.add(new BankTransaction(200, "EUR", true));

		int profit = eurCalculator.calculateProfit();
		int tax = eurCalculator.calculateTax();

		assertEquals(491, profit);
		assertEquals(40, tax);
	}

}
