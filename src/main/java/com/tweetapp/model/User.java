package com.tweetapp.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@Document(collection = "users")
public class User {

	@Id
	private String id;
	private String firstName;
	private String lastName;
	@Indexed(unique = true)
	private String email;
	@Indexed(unique = true)
	private String username;
	private String password;
	private long contactNumber;
	public User(String firstName, String lastName, String email, String username, String password,long contactNumber) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.username = username;
		this.password = password;
		this.contactNumber = contactNumber;
	}
	
	
	
	
	


}