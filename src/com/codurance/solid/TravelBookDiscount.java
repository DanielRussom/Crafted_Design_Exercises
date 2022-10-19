package com.codurance.solid;

import static com.codurance.solid.BookType.TRAVEL;

public class TravelBookDiscount implements BookDiscount {

	@Override
	public double getDiscountedPrice(BookCollection books) {
		double numberOfTravelBooks = books.getBooksOfType(TRAVEL).count();
		double totalPriceOfTravelBooks = books.getBooksOfType(TRAVEL).mapToDouble(Book::price).sum();
		double travelBooksDiscount = 0;
		
		if (numberOfTravelBooks > 3) {
			travelBooksDiscount = 0.6; // 40% discount when buying more than 3 travel books
		}

		if (travelBooksDiscount > 0) {
			totalPriceOfTravelBooks *= travelBooksDiscount;
		}
		
		return totalPriceOfTravelBooks;
	}
}
