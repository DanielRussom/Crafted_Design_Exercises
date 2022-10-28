package com.codurance.twitter.tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.codurance.twitter.Tweet;

class TweetShould {

	@Test
	void display_expected_message() {
		var expected = "expected_message";
		var underTest = new Tweet(1, "handle", expected);
		
		var result = underTest.message();
		
		assertEquals(expected, result);
	}

}
