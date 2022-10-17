package com.codurance.solid;

import static com.codurance.solid.BookType.TRAVEL;

public class TravelBookDiscount implements IBookDiscount {

	@Override
	public double getDiscountedPrice(BookCollection books) {
		double travel_books_discount = 0;
		double number_of_travel_books = 0;
		double total_price_for_travel_books = 0;

		number_of_travel_books = books.getBooks().stream().filter(book -> book.type().equals(TRAVEL)).count();
			
		for (Book book : books.getBooks()) {
			if (TRAVEL.equals(book.type())) {
				total_price_for_travel_books += book.price();
			}
		}

		if (number_of_travel_books > 3) {
			travel_books_discount = 0.6; // 40% discount when buying more than 3 travel books
		}

		if (travel_books_discount > 0) {
			total_price_for_travel_books *= travel_books_discount;
		}
		
		return total_price_for_travel_books;
	}

}
