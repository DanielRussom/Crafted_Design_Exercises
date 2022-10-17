package com.codurance.solid;

import static com.codurance.solid.BookType.IT;

public class ItBookDiscount implements IBookDiscount {

	@Override
	public double getDiscountedPrice(BookCollection books) {
		double number_of_it_books = books.getBooksOfType(IT).count();
		double total_price_for_it_books = books.getBooksOfType(IT).mapToDouble(Book::price).sum();
		double it_books_discount = 0;
		
		if (number_of_it_books > 0) {
			it_books_discount = 0.9; // 10% discount when buying up to 2 IT books
		}

		if (number_of_it_books > 2) {
			it_books_discount = 0.7; // 30% discount when buying more than 2 IT books
		} 
		
		if (it_books_discount > 0) {
			total_price_for_it_books *= it_books_discount;
		}
		
		return total_price_for_it_books;
	}
}
