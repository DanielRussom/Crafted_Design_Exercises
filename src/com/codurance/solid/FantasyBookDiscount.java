package com.codurance.solid;

import static com.codurance.solid.BookType.FANTASY;

public class FantasyBookDiscount implements BookDiscount {

	@Override
	public double getDiscountedPrice(BookCollection books) {
		double numberOfFantasyBooks = books.getBooksOfType(FANTASY).count();
		double totalPriceForFantasyBooks = books.getBooksOfType(FANTASY).mapToDouble(Book::price).sum();
		double fantasyBooksDiscount = 0;
		
		if (numberOfFantasyBooks >= 2) {
			fantasyBooksDiscount = 0.8; // 40% discount when buying more than 3 travel books
		}

		if (fantasyBooksDiscount > 0) {
			totalPriceForFantasyBooks *= fantasyBooksDiscount;
		}
		
		return totalPriceForFantasyBooks;
	}
}
