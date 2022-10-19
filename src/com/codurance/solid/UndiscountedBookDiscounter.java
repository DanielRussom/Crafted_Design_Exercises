package com.codurance.solid;

import static com.codurance.solid.BookType.FANTASY;
import static com.codurance.solid.BookType.IT;
import static com.codurance.solid.BookType.TRAVEL;

import java.util.Arrays;
import java.util.List;

public class UndiscountedBookDiscounter implements BookDiscount {

	private List<BookType> discountedBookTypes = Arrays.asList(IT, TRAVEL, FANTASY);
	@Override
	public double getDiscountedPrice(BookCollection books) {
		var undiscountedBooks = books.getBooks().stream().filter(book -> !discountedBookTypes.contains(book.type()));
		var total_price_for_undiscounted_books = undiscountedBooks.mapToDouble(Book::price).sum();
		
		return total_price_for_undiscounted_books;
	}

}
