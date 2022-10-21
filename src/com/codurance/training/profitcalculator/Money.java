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

	public Money calculateTax() {
        if (Value < 0) {
        	return new Money(0);
        }
		
        var taxValue = (int) (Value * 0.2);
		return new Money(taxValue);
	}

	public Money calculateProfit(int foreignMoney) {
		var profit = calculateValueAfterTax();
		profit.add(foreignMoney);
		return profit;
	}
}
