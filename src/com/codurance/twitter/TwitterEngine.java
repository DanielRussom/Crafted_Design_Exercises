package com.codurance.twitter;

import java.util.List;
import java.util.stream.Collectors;

public class TwitterEngine {

	private TweetDataStore _postData;

	public TwitterEngine(TweetDataStore postData) {
		_postData = postData;
	}

	public void post(String twitterId, String tweet) {
		if(tweet.length() >=  140) {
			throw new InvalidTweetException("Over character limit");
		}
		_postData.savePost(twitterId, tweet);
	}

	public List<Tweet> getTweetsFrom(String twitterId) {
		var allTweets = _postData.getAll();
		
		var tweetsFromId = allTweets.stream().filter(
				tweet -> tweet.twitterHandle.equals(twitterId))
				.sorted((firstTweet, secondTweet) -> secondTweet.id.compareTo(firstTweet.id))
				.collect(Collectors.toList());
		
		return tweetsFromId; 
	}

	public void formerFollowsLatter(String twitterId, String twitterIdToBeFollowed) {
		_postData.followUser(twitterId, twitterIdToBeFollowed);
	}

	public List<Tweet> getWallOf(String twitterId) {
		var allTweets = _postData.getAll();
		var followedUsers = _postData.getFollowedUsers(twitterId);
		followedUsers.add(twitterId);
		
		var tweetsFromWall = allTweets.stream().filter(
				tweet -> followedUsers.contains(tweet.twitterHandle))
				.sorted((firstTweet, secondTweet) -> secondTweet.id.compareTo(firstTweet.id))
				.collect(Collectors.toList());

		return tweetsFromWall;
	}
}
