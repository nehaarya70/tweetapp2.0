package com.tweetapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tweetapp.customException.UserAlreadyExist;
import com.tweetapp.customException.UserNotFoundException;
import com.tweetapp.helperClass.UserHelperClass;
import com.tweetapp.model.User;
import com.tweetapp.repository.UserRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class UserService {

	@Autowired
	UserRepository repository;

	public UserHelperClass createUser(User user) {
			log.info("Creating user started.");
			boolean checkEmailExist = checkEmailExist(user.getEmail());
			boolean checkUsernameExist = checkUsernameExist(user.getUsername());
			if(checkEmailExist) {
				throw new UserAlreadyExist("User with this email already exist !!!");
			}			
			if(checkUsernameExist) {
				throw new UserAlreadyExist("User with this username already exist !!!");
			}
			User userObj = repository.save(user);
		    UserHelperClass userHelperObj = new UserHelperClass(userObj.getId(),userObj.getEmail(), userObj.getUsername());
		    System.out.print(userObj);
		    log.info("Creating user finished.");
		    return userHelperObj;
	}

	public UserHelperClass loginUser(String username, String password) {
		log.info("Login user started.");
		User user = repository.findByUsernameAndPassword(username, password);
		if(user == null) {
			throw new UserNotFoundException("Invalid Login Details");
		}
		UserHelperClass userHelperObj = new UserHelperClass(user.getId(),user.getEmail(), user.getUsername());
		log.info("Login user finished.");
		return userHelperObj;
	}

	public boolean checkEmailExist(String email) {
		log.info("Checking existing mail started.");
		User user = repository.findByEmail(email);
		if(user == null) {
			log.info("Checking existing mail finished.");
			return false;
		}
		log.info("Checking existing mail finished.");
		return true;		
	}

	public boolean checkUsernameExist(String username) {
		log.info("Checking existing username started.");
		User user = repository.findByUsername(username);
		if(user == null) {
			log.info("Checking existing username finished.");
			return false;
		}
		log.info("Checking existing username finished.");
		return true;
	}

	public List<User> viewUsers() {
		log.info("Getting users started.");
		List<User> userList = repository.findAll();
		log.info("Getting users finished.");
		return userList;
	}

	public User resetPassword(String loginId, String newPassword) {
		log.info("Resetting password started.");
		User user = repository.findByUsername(loginId);
		if(user == null) {
			log.info("Resetting password finished.");
			throw new UserNotFoundException("User not Found");
		}
		user.setPassword(newPassword);
		log.info("Resetting password finished.");
		return repository.save(user);
	}

	public User getUserByUsername(String username) {
		log.info("Getting user started.");
		User user = repository.findByUsername(username);
		if(user == null) {
			throw new UserNotFoundException("User not Found");
		}
		log.info("Getting user finished.");
		return user;
	}

	public User forgetPassword(String username, String newPassword) {
		log.info("Forgot password operation started.");
		User user = repository.findByUsername(username);
		if(user==null) {
			throw new UserNotFoundException("User not Found");
		}
		user.setPassword(newPassword);
		log.info("Forgot password operation finished.");
		return repository.save(user);
	}
		
	public List<User> getUsersByRegex(String username){
		log.info("Getting all users by regex started.");
		List<User> users = repository.findUserWithName(username);
		if(users == null) {
			throw new UserNotFoundException("User not Found");
		}
		return users;
	}
}
