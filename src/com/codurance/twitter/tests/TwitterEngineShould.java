package com.codurance.twitter.tests;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import com.codurance.twitter.PostDataController;
import com.codurance.twitter.TwitterEngine;

import org.junit.Test;

public class TwitterEngineShould {

	@Test
	public void Store_post() {
		var postData = mock(PostDataController.class);
		var underTest = new TwitterEngine(postData);
		
		underTest.post("twitterId", "postText");
		
		verify(postData, times(1)).savePost(anyString(), anyString());
	}

}
