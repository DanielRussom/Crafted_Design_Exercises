package com.codurance.twitter.tests;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;

import com.codurance.twitter.InvalidTweetException;
import com.codurance.twitter.Tweet;
import com.codurance.twitter.TweetDataStore;
import com.codurance.twitter.TwitterEngine;

import org.junit.Test;

public class TwitterEngineShould {

	@Test
	public void Store_post() {
		var postData = mock(TweetDataStore.class);
		var underTest = new TwitterEngine(postData);
		
		underTest.post("twitterId", "postText");
		
		verify(postData, times(1)).savePost(anyString(), anyString());
	}

	@Test (expected = InvalidTweetException.class)
	public void Not_store_post_over_139_characters() {
		var postData = mock(TweetDataStore.class);
		var underTest = new TwitterEngine(postData);
		var postText = "";
		for(int i = 0; i < 140; i++) {
			postText += "A";
		}
		
		underTest.post("twitterId", postText);
	}
	
	@Test
	public void Get_tweets_from_specific_user() {
		var twitterId = "expectedHandle";
		var postData = mock(TweetDataStore.class);
		var testTweets = new ArrayList<Tweet>();
		testTweets.add(new Tweet(1, twitterId, "test"));
		testTweets.add(new Tweet(2, "incorrect", "test2"));
		testTweets.add(new Tweet(3, twitterId, "test3"));
		testTweets.add(new Tweet(4, twitterId, "test4"));
		when(postData.getAll()).thenReturn(testTweets);
		var underTest = new TwitterEngine(postData);
		
		var result = underTest.getTweetsFrom(twitterId);
		
		assertEquals(3, result.size());
		for(var tweet : result) {
			assertEquals(twitterId, tweet.twitterHandle);
		}
	}
	
	@Test
	public void Get_no_tweets() {
		var postData = mock(TweetDataStore.class);
		when(postData.getAll()).thenReturn(new ArrayList<Tweet>());
		var underTest = new TwitterEngine(postData);
		
		var result = underTest.getTweetsFrom("test");
		
		assertEquals(0, result.size());
	}

	@Test
	public void Get_tweets_in_reverse_chronological_order() {
		var twitterId = "expectedHandle";
		var postData = mock(TweetDataStore.class);
		var testTweets = new ArrayList<Tweet>();
		testTweets.add(new Tweet(1, twitterId, "test"));
		testTweets.add(new Tweet(2, twitterId, "test2"));
		when(postData.getAll()).thenReturn(testTweets);
		var underTest = new TwitterEngine(postData);
		
		var result = underTest.getTweetsFrom(twitterId);

		assertEquals(2, result.size());
		assertEquals(2, (int)result.get(0).id);
		assertEquals(1, (int)result.get(1).id);
	}
	


}
