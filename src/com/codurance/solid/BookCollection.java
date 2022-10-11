package com.codurance.solid;

import static java.util.Collections.unmodifiableList;

import java.util.ArrayList;
import java.util.List;

public class BookCollection {

	private List<Book> books = new ArrayList<>();
	
	public void add(Book item) {
		books.add(item);
	}
	
	public List<Book> getBooks() {
		return unmodifiableList(books);
	}
}
