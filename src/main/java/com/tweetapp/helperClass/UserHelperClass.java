package com.tweetapp.helperClass;

import lombok.Data;

@Data
public class UserHelperClass {
	
	private String id;
	private String email;
	private String username;
	public UserHelperClass(String id, String email, String username) {
		super();
		this.id = id;
		this.email = email;
		this.username = username;
	}

}
