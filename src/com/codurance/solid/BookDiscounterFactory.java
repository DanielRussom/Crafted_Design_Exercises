package com.codurance.solid;

import static com.codurance.solid.BookType.IT;
import static com.codurance.solid.BookType.FANTASY;
import static com.codurance.solid.BookType.COOKING;

import java.util.ArrayList;
import java.util.List;

public class BookDiscounterFactory {
	
	public ArrayList<BookDiscount> Generate(BookCollection books){
		var discounters = new ArrayList<BookDiscount>();
		
		if(books.getBooksOfType(IT).count() > 0) {
			discounters.add(new ItBookDiscount());
		};
		
		List<BookType> distinctBookTypes = books.getBooks().stream().map(x -> x.type()).distinct().toList();
		
		for(var bookTypes : distinctBookTypes) {
			switch(bookTypes) {
			case IT:
				discounters.add(new ItBookDiscount());
			case TRAVEL:
				discounters.add(new TravelBookDiscount());
			}
		}
		
		//if(distinctBookTypes.any(FANTASY) || distinctBookTypes.contains(COOKING))
		discounters.add(new UndiscountedBookDiscounter());

		return discounters;	
	}
}
