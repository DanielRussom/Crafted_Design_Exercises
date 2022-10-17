package com.codurance.solid;

import static com.codurance.solid.BookType.IT;
import static com.codurance.solid.BookType.TRAVEL;

import java.util.stream.Collectors;

public class DiscountCalculator {
	public double priceWithDiscount(BookCollection books) {
		var itBookDiscounter = new ItBookDiscount();
		var travelBookDiscounter = new TravelBookDiscount();
		
		var undiscountedBooks = books.getBooks().stream().filter(book -> !book.type().equals(IT) && !book.type().equals(TRAVEL));
		var total_price_for_undiscounted_books = undiscountedBooks.mapToDouble(Book::price).sum();
		
		return itBookDiscounter.getDiscountedPrice(books)
				+ travelBookDiscounter.getDiscountedPrice(books)
				+ total_price_for_undiscounted_books;
	}
}
