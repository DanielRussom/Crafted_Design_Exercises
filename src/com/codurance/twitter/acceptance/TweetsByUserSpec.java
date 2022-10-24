package com.codurance.twitter.acceptance;

import com.codurance.twitter.Tweet;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class TweetsByUserSpec extends BaseTwitterSpec {

	@Test public void
	should_return_all_tweets_from_a_user_in_reverse_chronological_order() {
		post(SANDRO, SANDRO_FIRST_TWEET);
		post(SAMIR,  SAMIR_FIRST_TWEET);
		post(SANDRO, SANDRO_SECOND_TWEET);
		post(MASH,   MASH_FIRST_TWEET);

		List<Tweet> tweets = tweetsFrom(SANDRO);

		assertEquals(2, tweets.size());
		assertEquals(SANDRO_SECOND_TWEET, tweets.get(0).message());
		assertEquals(SANDRO_FIRST_TWEET, tweets.get(1).message());
	}

}
