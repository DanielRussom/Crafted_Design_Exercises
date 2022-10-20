package com.codurance.training.profitcalculator;

public class BankTransaction {

	private int amount;
	private String currency;
	private boolean incoming;

	public BankTransaction(int amount, String currency, boolean incoming) {
		this.amount = amount;
		this.currency = currency;
		this.incoming = incoming;
	}

	public int getAmount() {
		return amount;
	}

	public boolean isOutgoing() {
		return !incoming;
	}

	public String getCurrency() {
		return currency;
	}

}
