package com.codurance.solid;

import java.util.List;

import static java.lang.Math.round;

public class Basket {

	private BookCollection books = new BookCollection(new DiscountCalculator(new BookDiscounterFactory()));

	public void add(Book item) {
		books.add(item);
	}

	public List<Book> books() {
		return books.getBooks();
	}
	
	public double priceWithDiscount() {
		return toDecimal(books.priceWithDiscount());
	}

	public double fullPrice() {
		return toDecimal(books.fullPrice());
	}

	private double toDecimal(double number) {
		return round(number * 100) / 100.0;
	}
}
