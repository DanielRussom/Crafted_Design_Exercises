package com.codurance.twitter;

public class Tweet {

	private String message;
	public String twitterHandle;
	public Integer id;

	public Tweet(int id, String twitterHandle, String message) {
		this.message = message;
		this.twitterHandle = twitterHandle;
		this.id = id;
	}

	public String message() {
		return message;
	}

}
