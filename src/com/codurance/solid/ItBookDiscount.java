package com.codurance.solid;

import static com.codurance.solid.BookType.IT;

public class ItBookDiscount implements IBookDiscount {

	@Override
	public double getDiscountedPrice(BookCollection books) {
		double it_books_discount = 0;
		double number_of_it_books = 0;
		double total_price_for_it_books = 0;
		
		number_of_it_books = books.getBooks().stream().filter(book -> book.type().equals(IT)).count();
		
		for (Book book : books.getBooks()) {
			if (IT.equals(book.type())) {
				total_price_for_it_books += book.price();
			}
		}
		
		if (number_of_it_books > 2) {
			it_books_discount = 0.7; // 30% discount when buying more than 2 IT books
		} else if (number_of_it_books > 0) {
			it_books_discount = 0.9; // 10% discount when buying up to 2 IT books
		}
		if (it_books_discount > 0) {
			total_price_for_it_books *= it_books_discount;
		}
		
		return total_price_for_it_books;
	}

}
