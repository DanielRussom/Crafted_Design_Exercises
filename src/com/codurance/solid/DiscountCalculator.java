package com.codurance.solid;

import static com.codurance.solid.BookType.IT;
import static com.codurance.solid.BookType.TRAVEL;

import java.util.stream.Collectors;

public class DiscountCalculator {
	public double priceWithDiscount(BookCollection books) {
		double it_books_discount = 0;
		double travel_books_discount = 0;
		
		double number_of_it_books = 0;
		double number_of_travel_books = 0;
		
		double total_price_for_it_books = 0;
		double total_price_for_travel_books = 0;
		double total_price_for_other_books = 0;
		
		number_of_it_books = books.getBooks().stream().filter(book -> book.type().equals(IT)).count();
		number_of_travel_books = books.getBooks().stream().filter(book -> book.type().equals(TRAVEL)).count();
				
		for (Book book : books.getBooks()) {
			if (IT.equals(book.type())) {
				total_price_for_it_books += book.price();
			} else if (TRAVEL.equals(book.type())) {
				total_price_for_travel_books += book.price();
			} else {
				total_price_for_other_books += book.price();
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
		
		if (number_of_travel_books > 3) {
			travel_books_discount = 0.6; // 40% discount when buying more than 3 travel books
		}

		if (travel_books_discount > 0) {
			total_price_for_travel_books *= travel_books_discount;
		}

		return total_price_for_it_books
				+ total_price_for_travel_books
				+ total_price_for_other_books;
	}
}
