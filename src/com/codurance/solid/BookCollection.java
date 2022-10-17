package com.codurance.solid;

import static java.util.Collections.unmodifiableList;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class BookCollection {
	public BookCollection(DiscountCalculator calculator) {
		discountCalculator = calculator;
	}

	private List<Book> books = new ArrayList<>();

	private DiscountCalculator discountCalculator;
	
	public void add(Book item) {
		books.add(item);
	}
	
	public List<Book> getBooks() {
		return unmodifiableList(books);
	}
		
	public double priceWithDiscount() {
		return discountCalculator.priceWithDiscount(this);
	}

	public double fullPrice() {
		double price = 0;
		for (Book book : books) {
			price += book.price();
		}
		
		return price;
	}
	
	public Stream<Book> getBooksOfType(BookType type){
		return books.stream().filter(book -> book.type().equals(type));
	}
}
