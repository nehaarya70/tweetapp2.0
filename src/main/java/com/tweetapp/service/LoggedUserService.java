package com.tweetapp.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tweetapp.model.LoggedUser;
import com.tweetapp.repository.LoggedUserRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class LoggedUserService {

	@Autowired
	LoggedUserRepository repository;

	public LoggedUser createLoggedUser(Date loginTime, String username) {
		log.info("Creating logged user.");
		return repository.save(new LoggedUser(loginTime, username));
	}

	public void deleteLoggedUser(String email) {
		log.info("Deleting user.");
		repository.deleteByEmailId(email);
	}

}
