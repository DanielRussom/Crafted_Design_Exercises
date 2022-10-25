package com.codurance.twitter;

import java.util.ArrayList;
import java.util.List;

public class TweetDataStore {

	public void savePost(String twitterId, String tweet) {
		
	}

	public List<Tweet> getAll() {
		var list = new ArrayList<Tweet>();
		list.add(new Tweet(1, "", ""));
		
		return list;
	}

}
