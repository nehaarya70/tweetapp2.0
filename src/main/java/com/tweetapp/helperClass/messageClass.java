package com.tweetapp.helperClass;

import lombok.Data;

@Data
public class messageClass {
	String username;
	String message;
	public messageClass(String username, String message) {
		super();
		this.username = username;
		this.message = message;
	}
   
}
