package com.codurance.twitter;

import java.util.ArrayList;
import java.util.List;

public class TwitterEngine {

	private TweetDataStore _postData;

	public TwitterEngine(TweetDataStore postData) {
		_postData = postData;
	}

	public void post(String twitterId, String tweet) {
		if(tweet.length() < 140) {
			_postData.savePost(twitterId, tweet);
		}
	}

	public List<Tweet> getTweetsFrom(String twitterId) {
		var firstTweet =_postData.getAll().get(0);
		
		var test = new ArrayList<Tweet>();
		test.add(firstTweet);
		
		return test; 
	}
}
