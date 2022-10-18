package com.codurance.solid;

import static com.codurance.solid.BookType.IT;

public class ItBookDiscount implements BookDiscount {

	@Override
	public double getDiscountedPrice(BookCollection books) {
		double numberOfItBooks = books.getBooksOfType(IT).count();
		double totalPriceOfItBooks = books.getBooksOfType(IT).mapToDouble(Book::price).sum();
		double itBooksDiscount = 0;
		
		if (numberOfItBooks > 0) {
			itBooksDiscount = 0.9; // 10% discount when buying up to 2 IT books
		}

		if (numberOfItBooks > 2) {
			itBooksDiscount = 0.7; // 30% discount when buying more than 2 IT books
		} 
		
		if (itBooksDiscount > 0) {
			totalPriceOfItBooks *= itBooksDiscount;
		}
		
		return totalPriceOfItBooks;
	}
}
