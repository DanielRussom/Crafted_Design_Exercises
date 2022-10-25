package com.codurance.twitter;

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
}
