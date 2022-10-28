package com.codurance.twitter;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class TweetDataStore {
	ArrayList<Tweet> tweets = new ArrayList<Tweet>();
	ArrayList<FollowedUser> followedUsers = new ArrayList<FollowedUser>();
	
	public void savePost(String twitterId, String tweet) {
		var tweetId = tweets.size() + 1;
		
		tweets.add(new Tweet(tweetId, twitterId, tweet));
	}

	public List<Tweet> getAll() {
		return tweets;
	}

	public void followUser(String sourceUser, String userToFollow) {
		var followedUser = new FollowedUser(sourceUser, userToFollow);
		followedUsers.add(followedUser);	
	}

	public List<String> getFollowedUsers(String sourceUser) {		
		var sourceFollowPairings = followedUsers.stream()
				.filter(m -> m.isSource(sourceUser))
				.map(m -> m.getTarget())
				.collect(Collectors.toList());
		
		return sourceFollowPairings;
	}
}
