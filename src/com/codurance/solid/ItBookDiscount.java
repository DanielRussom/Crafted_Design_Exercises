package com.codurance.solid;

import static com.codurance.solid.BookType.IT;

import java.util.stream.Stream;

public class ItBookDiscount implements IBookDiscount {

	@Override
	public double getDiscountedPrice(BookCollection books) {
		double number_of_it_books = getItBooks(books).toList().size();
		double total_price_for_it_books = getItBooks(books).mapToDouble(Book::price).sum();
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

	private Stream<Book> getItBooks(BookCollection books) {
		var itBooks = books.getBooks().stream().filter(book -> book.type().equals(IT));
		return itBooks;
	}
}
