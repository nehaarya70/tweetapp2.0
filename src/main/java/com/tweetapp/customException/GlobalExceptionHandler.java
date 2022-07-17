package com.tweetapp.customException;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.tweetapp.model.CustomErrorResponse;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler({ UserNotFoundException.class })
	public ResponseEntity<CustomErrorResponse> handleConsumerNotFoundException(UserNotFoundException ex) {
		CustomErrorResponse response = new CustomErrorResponse();
		response.setTimestamp(LocalDateTime.now());
		response.setMessage(ex.getMessage());
		response.setStatus(HttpStatus.NOT_FOUND);
		response.setReason("Invalid Login details Provided");
		return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler({ UserAlreadyExist.class })
	public ResponseEntity<CustomErrorResponse> handleConsumerExistException(UserAlreadyExist ex) {
		CustomErrorResponse response = new CustomErrorResponse();
		response.setTimestamp(LocalDateTime.now());
		response.setMessage(ex.getMessage());
		response.setStatus(HttpStatus.NOT_ACCEPTABLE);
		response.setReason("User Already Exist with the provided Username");
		return new ResponseEntity<>(response, HttpStatus.NOT_ACCEPTABLE);
	}
	
	@ExceptionHandler({ TweetNotFoundException.class })
	public ResponseEntity<CustomErrorResponse> handleTweetNotFoundException(TweetNotFoundException ex) {
		CustomErrorResponse response = new CustomErrorResponse();
		response.setTimestamp(LocalDateTime.now());
		response.setMessage(ex.getMessage());
		response.setStatus(HttpStatus.NOT_FOUND);
		response.setReason("Tweet Not Found");
		return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
	}
}
