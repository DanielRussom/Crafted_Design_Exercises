package com.codurance.twitter;

import java.util.ArrayList;
import java.util.List;

public class TweetDataStore {
	ArrayList<Tweet> tweets = new ArrayList<Tweet>();
	
	public void savePost(String twitterId, String tweet) {
		var tweetId = tweets.size() + 1;
		
		tweets.add(new Tweet(tweetId, twitterId, tweet));
	}

	public List<Tweet> getAll() {
		return tweets;
	}
}
