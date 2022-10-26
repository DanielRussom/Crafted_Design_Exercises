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
		var tweetsFromId = new ArrayList<Tweet>();
		var allTweets = _postData.getAll();
		
		for(var tweet : allTweets) {
			if(tweet.twitterHandle.equals(twitterId)) {
				tweetsFromId.add(tweet);
			}
		}
		
		return tweetsFromId; 
	}
}
