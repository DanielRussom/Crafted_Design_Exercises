package com.codurance.solid;

import static com.codurance.solid.BookType.COOKING;

import java.util.Arrays;
import java.util.List;

public class UndiscountedBookDiscounter implements BookDiscount {

	private List<BookType> undiscountedBookTypes = Arrays.asList(COOKING);
	@Override
	public double getDiscountedPrice(BookCollection books) {
		var undiscountedBooks = books.getBooks().stream().filter(book -> undiscountedBookTypes.contains(book.type()));
		var totalPriceOfUndiscountedBooks = undiscountedBooks.mapToDouble(Book::price).sum();
		
		return totalPriceOfUndiscountedBooks;
	}

}
