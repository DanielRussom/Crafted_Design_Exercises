package com.codurance.solid;

import static com.codurance.solid.BookType.FANTASY;
import static com.codurance.solid.BookType.IT;
import static com.codurance.solid.BookType.TRAVEL;

import java.util.ArrayList;

public class BookDiscounterFactory {
	
	public ArrayList<BookDiscount> Generate(BookCollection books){
		var discounters = new ArrayList<BookDiscount>();
		
		if(books.getBooksOfType(IT).count() > 0) {
			discounters.add(new ItBookDiscount());
		};
		
		if(books.getBooksOfType(TRAVEL).count() > 0) {
			discounters.add(new TravelBookDiscount());
		};
		
		if(books.getBooksOfType(FANTASY).count() > 0) {
			discounters.add(new FantasyBookDiscount());
		};
		
		discounters.add(new UndiscountedBookDiscounter());
				
		return discounters;	
	}
}
