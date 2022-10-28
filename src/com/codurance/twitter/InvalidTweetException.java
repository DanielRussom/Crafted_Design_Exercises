package com.codurance.twitter;

public class InvalidTweetException extends RuntimeException{

    public InvalidTweetException(String message) {
        super("Invalid Tweet: " + message);
    }
    
	private static final long serialVersionUID = -216464852757297933L;

}
