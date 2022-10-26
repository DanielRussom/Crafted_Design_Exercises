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
		var content = "content";
		var twitterId = "twitterId";
		
		underTest.savePost(twitterId, content);

		List<Tweet> result = underTest.getAll();
		assertEquals(1, result.size());
		var firstTweet = result.get(0);
		assertEquals(1, firstTweet.id);
		assertEquals(content, firstTweet.message);
		assertEquals(twitterId, firstTweet.twitterHandle);
	}

	@Test
	void save_multiple_tweets() {
		TweetDataStore underTest = new TweetDataStore();
		var content1 = "content";
		var twitterId1 = "twitterId";
		var content2 = "content2";
		var twitterId2 = "twitterId2";
		
		underTest.savePost(twitterId1, content1);
		underTest.savePost(twitterId2, content2);

		List<Tweet> result = underTest.getAll();
		assertEquals(2, result.size());
		var firstTweet = result.get(0);
		assertEquals(1, firstTweet.id);
		assertEquals(content1, firstTweet.message);
		assertEquals(twitterId1, firstTweet.twitterHandle);
		var secondTweet = result.get(1);
		assertEquals(2, secondTweet.id);
		assertEquals(content2, secondTweet.message);
		assertEquals(twitterId2, secondTweet.twitterHandle);
	}
}
