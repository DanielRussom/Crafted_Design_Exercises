package com.codurance.twitter.tests;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

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

	@Test
	public void Not_store_post_over_139_characters() {
		var postData = mock(TweetDataStore.class);
		var underTest = new TwitterEngine(postData);
		var postText = "";
		for(int i = 0; i < 140; i++) {
			postText += "A";
		}
		
		underTest.post("twitterId", postText);
		
		verify(postData, times(0)).savePost(anyString(), anyString());
	}


}
