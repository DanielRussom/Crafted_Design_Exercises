package com.codurance.twitter.tests;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;

import com.codurance.twitter.Tweet;
import com.codurance.twitter.TweetDataStore;

class TweetDataStoreShould {

	@Test
	void save_a_tweet() {
		TweetDataStore underTest = new TweetDataStore();

		underTest.savePost("twitterId", "content");

		List<Tweet> result = underTest.getAll();
		assertEquals(1, result.size());
	}

}
