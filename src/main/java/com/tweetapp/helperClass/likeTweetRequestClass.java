package com.tweetapp.helperClass;

public class likeTweetRequestClass {
	
	String username;
	String id;
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public likeTweetRequestClass(String username, String id) {
		super();
		this.username = username;
		this.id = id;
	}
}
