package com.tweetapp.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tweetapp.activemq.JmsProducer;
import com.tweetapp.helperClass.UserHelperClass;
import com.tweetapp.helperClass.forgetPasswordHelperClass;
import com.tweetapp.helperClass.messageClass;
import com.tweetapp.model.LikeTweet;
import com.tweetapp.model.ReplyTweet;
import com.tweetapp.model.Tweet;
import com.tweetapp.model.User;
import com.tweetapp.service.TweetService;
import com.tweetapp.service.UserService;

@RestController
@RequestMapping(value = "/tweetApp/2.0")
@CrossOrigin(origins = "*")
public class TweetAppController {

	@Autowired
	TweetService tweetService;
	@Autowired
	UserService userService;

	@Autowired
	JmsProducer jmsProducer;

	@RequestMapping(value = "/healthCheck", method = RequestMethod.GET)
	public String healthCheck() {
		jmsProducer.sendMessage("Healthy!");
		return "Healthy";
	}

	@RequestMapping(value = "/registerUser", method = RequestMethod.POST)
	public UserHelperClass register(@RequestBody User user) {
		UserHelperClass createdUser = userService.createUser(user);
		jmsProducer.sendMessage(createdUser.toString());
		return createdUser;
	}

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public UserHelperClass login(@RequestParam String username, @RequestParam String password) {
		jmsProducer.sendMessage("Logging user : "+ username);
		return userService.loginUser(username, password);
	}

	@RequestMapping(value = "/forgetPassword", method = RequestMethod.PUT)
	public User forgetPassword(@RequestBody forgetPasswordHelperClass forgetPasswordObj) {
		jmsProducer.sendMessage("Password Reset is going on  . . .");
		return userService.forgetPassword(forgetPasswordObj.getUsername(), forgetPasswordObj.getNewPassword());
	}

	@RequestMapping(value = "/users/allUsers", method = RequestMethod.GET)
	public List<User> getAllUsers() {
		List<User> listOfUsers= userService.viewUsers();
		jmsProducer.sendMessage(listOfUsers.toString());
		return listOfUsers;
	}
	
	@RequestMapping(value = "/oneTweet/{id}", method = RequestMethod.GET)
	public Optional<Tweet> getOneTweet(@PathVariable String id) {
		Optional<Tweet> tweet = tweetService.getOneTweet(id);
		jmsProducer.sendMessage(tweet.toString());
		return tweet;
	}

	@RequestMapping(value = "/allTweets", method = RequestMethod.GET)
	public List<Tweet> getAllTweets() {
		List<Tweet> listOfTweets = tweetService.viewAllTweet();
		jmsProducer.sendMessage(listOfTweets.toString());
		return listOfTweets;
	}

	@RequestMapping(value = "/user/searchAppUser/{username}", method = RequestMethod.GET)
	public List<User> saerchUserByUserName(@PathVariable String username) {
		List<User> users = userService.getUsersByRegex(username);
		jmsProducer.sendMessage(users.toString());
		return users;
	}

	@RequestMapping(value = "/{username}", method = RequestMethod.GET)
	public List<Tweet> getAllTweetsOfAUser(@PathVariable String username) {
		List<Tweet> userTweets = tweetService.viewMyTweet(username);
		jmsProducer.sendMessage(userTweets.toString());
		return userTweets;
	}

	@RequestMapping(value = "/addTweet", method = RequestMethod.POST)
	public Tweet postNewTweet(@RequestBody messageClass messageObj) {
		Tweet tweet = tweetService.createTweet(messageObj.getUsername(), messageObj.getMessage());
		jmsProducer.sendMessage(tweet.toString());
		return tweet;
	}

	@RequestMapping(value = "/updateTweet/{id}", method = RequestMethod.PUT)
	public Tweet updateTweet(@PathVariable String id, @RequestBody messageClass message) {
		Tweet tweet = tweetService.updateTweet(message.getUsername(), id, message.getMessage());
		jmsProducer.sendMessage(tweet.toString());
		return tweet;
	}

	@RequestMapping(value = "/{username}/deleteTweet/{id}", method = RequestMethod.DELETE)
	public messageClass deleteTweet(@PathVariable String username, @PathVariable String id) {
		messageClass messageClass = tweetService.deleteTweet(username, id);
		jmsProducer.sendMessage(messageClass.toString());
		return messageClass;
	}

	@RequestMapping(value = "/likeTweet", method = RequestMethod.PUT)
	public LikeTweet likeTweetByUser(@RequestBody LikeTweet likeTweetObj) {
		LikeTweet likedTweet = tweetService.likeTweetByUser(likeTweetObj);
		jmsProducer.sendMessage(likedTweet.toString());
		return likedTweet;
	}

	@RequestMapping(value = "/unlikeTweet/{id}/{username}/{likedByUser}", method = RequestMethod.DELETE)
	public LikeTweet unlikeTweetByUser(@PathVariable String id, @PathVariable String username, @PathVariable String likedByUser) {
		LikeTweet likedTweet = tweetService.unlikeTweetByUser(id, username, likedByUser);
		jmsProducer.sendMessage(likedTweet.toString());
		return likedTweet;
	}
		
	@RequestMapping(value = "/getTweetsLikedByUser/{username}", method = RequestMethod.GET)
	public List<LikeTweet> getTweetsLikedByUser(@PathVariable String username) {
		List<LikeTweet> likedTweets = tweetService.getTweetsLikedByUser(username);
		jmsProducer.sendMessage(likedTweets.toString());
		return likedTweets;
	}
	
	@RequestMapping(value = "/getLikedTweets", method = RequestMethod.GET)
	public List<LikeTweet> getLikedTweets() {
		List<LikeTweet> likedTweets = tweetService.getLikedTweets();
		jmsProducer.sendMessage(likedTweets.toString());
		return likedTweets;
	}
	
	@RequestMapping(value = "/replyTweet", method = RequestMethod.PUT)
	public ReplyTweet replyTweetByUser(@RequestBody ReplyTweet replyTweetObj) {
		ReplyTweet likedTweet = tweetService.ReplyTweetByUser(replyTweetObj);
		jmsProducer.sendMessage(likedTweet.toString());
		return likedTweet;
	}
	
	@RequestMapping(value = "/getReplyTweets", method = RequestMethod.GET)
	public List<ReplyTweet> getreplyTweets() {
		List<ReplyTweet> replyTweets = tweetService.getReliedTweets();
		jmsProducer.sendMessage(replyTweets.toString());
		return replyTweets;
	}

	@RequestMapping(value = "/getRepliesById/{id}", method = RequestMethod.GET)
	public List<ReplyTweet> getRepliesById(@PathVariable String id) {
		List<ReplyTweet> replyTweets = tweetService.getRepliesById(id);
		jmsProducer.sendMessage(replyTweets.toString());
		return replyTweets;
	}
	
}
