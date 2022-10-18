package com.codurance.solid;

import static com.codurance.solid.BookType.IT;
import static com.codurance.solid.BookType.TRAVEL;

import java.util.Arrays;
import java.util.List;

import org.hamcrest.Factory;

public class DiscountCalculator {
	private List<BookType> discountedBookTypes = Arrays.asList(IT, TRAVEL);
		
	public double priceWithDiscount(BookCollection books) {
		/*var discounters = factory.create(books);
			(inside Factory based on books contents)
			var discounters = new List<BookDiscouner>{
				itBookDiscounter
				travelDiscounter
			}
			
			
		foreach discounter
			discounter.getDiscountedPrice
		*/
		var itBookDiscounter = new ItBookDiscount();
		var travelBookDiscounter = new TravelBookDiscount();
		
		var total_price_for_undiscounted_books = getUndiscountedBooksPrice(books);
		
		return itBookDiscounter.getDiscountedPrice(books)
				+ travelBookDiscounter.getDiscountedPrice(books)
				+ total_price_for_undiscounted_books;
	}

	private double getUndiscountedBooksPrice(BookCollection books) {
		var undiscountedBooks = books.getBooks().stream().filter(book -> !discountedBookTypes.contains(book.type()));
		var total_price_for_undiscounted_books = undiscountedBooks.mapToDouble(Book::price).sum();
		
		return total_price_for_undiscounted_books;
	}
}
