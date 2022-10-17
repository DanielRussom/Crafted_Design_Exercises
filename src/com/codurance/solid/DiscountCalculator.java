package com.codurance.solid;

import static com.codurance.solid.BookType.IT;
import static com.codurance.solid.BookType.TRAVEL;

import java.util.stream.Collectors;

public class DiscountCalculator {
	public double priceWithDiscount(BookCollection books) {
		var itBookDiscounter = new ItBookDiscount();
		var travelBookDiscounter = new TravelBookDiscount();
		
		double total_price_for_undiscounted_books = 0;
		
		for (Book book : books.getBooks()) {
			if (!IT.equals(book.type()) && !TRAVEL.equals(book.type())){
				total_price_for_undiscounted_books += book.price();
			}
		}

		return itBookDiscounter.getDiscountedPrice(books)
				+ travelBookDiscounter.getDiscountedPrice(books)
				+ total_price_for_undiscounted_books;
	}
}
