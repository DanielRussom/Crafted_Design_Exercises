package com.codurance.twitter.acceptance;

import com.codurance.twitter.Tweet;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class WallSpec extends BaseTwitterSpec {

	@Test
	public void
	should_return_all_tweets_from_a_user_in_reverse_chronological_order() {
		post(SANDRO, SANDRO_FIRST_TWEET);
		post(SAMIR,  SAMIR_FIRST_TWEET);
		post(SANDRO, SANDRO_SECOND_TWEET);
		post(PEDRO,  PEDRO_FIRST_TWEET);
		post(MASH,   MASH_FIRST_TWEET);
		post(STEVE,  STEVE_FIRST_TWEET);
		post(STEVE,  STEVE_SECOND_TWEET);
		post(SAMIR,  SAMIR_SECOND_TWEET);

		formerFollowsLatter(STEVE, SAMIR);
		formerFollowsLatter(STEVE, PEDRO);

		List<Tweet> tweets = wallOf(STEVE);

		assertEquals(5, tweets.size());
		assertEquals(SAMIR_SECOND_TWEET, tweets.get(0).message());
		assertEquals(STEVE_SECOND_TWEET, tweets.get(1).message());
		assertEquals(STEVE_FIRST_TWEET, tweets.get(2).message());
		assertEquals(PEDRO_FIRST_TWEET, tweets.get(3).message());
		assertEquals(SAMIR_FIRST_TWEET, tweets.get(4).message());
	}

}
