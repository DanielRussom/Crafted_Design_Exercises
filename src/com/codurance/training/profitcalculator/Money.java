package com.codurance.training.profitcalculator;

public class Money {

	public int Value;

	public Money(int value) {
		Value = value;
	}

	public void add(int valueToAdd) {
		Value += valueToAdd;
	}

	public Money calculateValueAfterTax() {
		return new Money(Value - calculateTax().Value);
	}

	private Money calculateTax() {
		var taxValue = 0;
        if (Value >= 0) {
        	taxValue = (int) (Value * 0.2);
        }

        return new Money(taxValue);
	}
}
