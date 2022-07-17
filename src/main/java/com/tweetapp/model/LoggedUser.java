package com.tweetapp.model;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@Document(collection = "loggedusers")
public class LoggedUser {

	@Id
	private String id;
	private Date loginTime;
	private String emailId;
	public LoggedUser(Date loginTime, String emailId) {
		super();
		this.loginTime = loginTime;
		this.emailId = emailId;
	}	
}
