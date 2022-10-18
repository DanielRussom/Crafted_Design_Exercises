package com.codurance.solid;

public class DiscountCalculator {
	private BookDiscounterFactory _discounterFactory;
			
	public DiscountCalculator(BookDiscounterFactory discounterFactory) {
		_discounterFactory = discounterFactory;
	}

	public double priceWithDiscount(BookCollection books) {
		var discounters = _discounterFactory.Generate(books);
		
		double price = 0;
		
		for(var discounter : discounters) {
			price += discounter.getDiscountedPrice(books);
		}
		
		return price;
	}
}
