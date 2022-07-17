package com.tweetapp.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.tweetapp.model.LoggedUser;

public interface LoggedUserRepository extends MongoRepository<LoggedUser, String> {

	public void deleteByEmailId(String email);
	
	
}
