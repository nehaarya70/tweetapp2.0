package com.tweetapp.customException;

public class UserAlreadyExist extends RuntimeException {

	private static final long serialVersionUID = -2862505141325062716L;

	public UserAlreadyExist() {
		super();
	}

	public UserAlreadyExist(String message) {
		super(message);
	}

}
