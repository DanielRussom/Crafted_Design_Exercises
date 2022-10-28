package com.codurance.twitter;

public class FollowedUser {

	private String sourceUser;
	private String userToFollow;

	public FollowedUser(String sourceUser, String userToFollow) {
		this.sourceUser = sourceUser;
		this.userToFollow = userToFollow;
	}

	public boolean isSource(String userToCompare) {
		return sourceUser.equals(userToCompare);
	}

	public String getTarget() {
		return userToFollow;
	}
}
