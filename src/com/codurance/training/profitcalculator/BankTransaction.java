package com.codurance.training.profitcalculator;

public class BankTransaction {

	private int amount;
	private Currency currency;
	private boolean incoming;

	public BankTransaction(int amount, Currency gbp, boolean incoming) {
		this.amount = amount;
		this.currency = gbp;
		this.incoming = incoming;
	}

	public int getAmount() {
		return amount;
	}

	public boolean isOutgoing() {
		return !incoming;
	}

	public Currency getCurrency() {
		return currency;
	}

}
