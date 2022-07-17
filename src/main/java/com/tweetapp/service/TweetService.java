package com.tweetapp.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.tweetapp.customException.TweetNotFoundException;
import com.tweetapp.customException.UserNotFoundException;
import com.tweetapp.helperClass.messageClass;
import com.tweetapp.model.LikeTweet;
import com.tweetapp.model.ReplyTweet;
import com.tweetapp.model.Tweet;
import com.tweetapp.model.User;
import com.tweetapp.repository.LikeTweetRepository;
import com.tweetapp.repository.ReplyTweetRepository;
import com.tweetapp.repository.TweetRepository;
import com.tweetapp.repository.UserRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class TweetService {

	@Autowired
	TweetRepository tweetRepository;
	@Autowired
	UserRepository repository;
	@Autowired
	LikeTweetRepository likeTweetRepository;
	@Autowired
	ReplyTweetRepository replyTweetRepository;

	public Tweet createTweet(String username, String message) {
		log.info("Creating tweet started.");
		User user = repository.findByUsername(username);
		if (user == null) {
			throw new UserNotFoundException("User not Found");
		}

		Date date = new Date();
		int noOfLikes = 0;
		log.info("Creating tweet finished.");
		return tweetRepository.save(new Tweet(date, username, message, noOfLikes));

	}

	public List<Tweet> viewAllTweet() {
		log.info("Getting all tweets started.");
		List<Tweet> listOfTweet = tweetRepository.findAll(Sort.by(Sort.Direction.DESC, "createdDateTime"));;
		if (listOfTweet == null) {
			throw new TweetNotFoundException("No Tweet Found");
		}
		log.info("Getting all tweets finished.");
		return listOfTweet;

	}

	public List<Tweet> viewMyTweet(String username) {
		log.info("Getting user tweets started.");
		List<Tweet> listOfTweet = tweetRepository.findByUsername(username);
		if (listOfTweet == null) {
			throw new TweetNotFoundException("No Tweet Found");
		}
		log.info("Getting user tweets finished.");
		return listOfTweet;

	}

	public Tweet updateTweet(String username, String Id, String message) {
		log.info("Updating tweet started.");
		Tweet tweet = tweetRepository.findByUsernameAndId(username, Id);
		if (tweet == null) {
			throw new TweetNotFoundException("No Tweet Found");
		}
		tweet.setMessage(message);
		log.info("Updating tweet finished.");
		return tweetRepository.save(tweet);

	}

	public messageClass deleteTweet(String username, String Id) {
		log.info("Deleting tweet started.");
		Tweet tweet = tweetRepository.findByUsernameAndId(username, Id);
		if (tweet == null) {
			throw new TweetNotFoundException("No Tweet Found");
		}
		tweetRepository.deleteById(Id);
		messageClass message = new messageClass(username,"Tweet"+ tweet.getId()+ "Deleted Successfully");
		log.info("Deleting tweet finished.");
		return message;
	}


	public Tweet likeTweet(String username, String id) {
		log.info("Liking tweet started.");
		Tweet tweet = tweetRepository.findByUsernameAndId(username, id);
		if (tweet == null) {
			throw new TweetNotFoundException("No Tweet Found");
		}
		int currentNoOfLikes = tweet.getNoOfLikes();
		int updatedNoOfLikes = currentNoOfLikes + 1;
		tweet.setNoOfLikes(updatedNoOfLikes);
		log.info("Liking tweet finished.");
		return tweetRepository.save(tweet);

	}

	public LikeTweet likeTweetByUser(LikeTweet likeTweetObj) {
		// TODO Auto-generated method stub
		log.info("Liking tweet started.");
		Tweet tweet = tweetRepository.findByUsernameAndId(likeTweetObj.getUser(), likeTweetObj.getTweetId());
		if (tweet == null) {
			throw new TweetNotFoundException("No Tweet Found");
		}
		int currentNoOfLikes = tweet.getNoOfLikes();
		int updatedNoOfLikes = currentNoOfLikes + 1;
		tweet.setNoOfLikes(updatedNoOfLikes);
		log.info("Liking tweet finished.");
		tweetRepository.save(tweet);
		
		return likeTweetRepository.save(likeTweetObj);
	}
	
	public LikeTweet unlikeTweetByUser(String id, String username, String likedByUser) {
		// TODO Auto-generated method stub
		log.info("Unliking tweet started.");
		Tweet tweet = tweetRepository.findByUsernameAndId(username, id);
		if (tweet == null) {
			throw new TweetNotFoundException("No Tweet Found");
		}
		int currentNoOfLikes = tweet.getNoOfLikes();
		int updatedNoOfLikes = currentNoOfLikes - 1;
		tweet.setNoOfLikes(updatedNoOfLikes);
		log.info("Unliking tweet finished.");
		tweetRepository.save(tweet);
		return likeTweetRepository.deleteByIdAndUsername(id, likedByUser);
	}
	
	public List<LikeTweet> getTweetsLikedByUser(String username) {
		// TODO Auto-generated method stub
		log.info("Getting all tweets liked by user started.");
		username = "^"+username+"$";
		List<LikeTweet> listOfTweet = likeTweetRepository.findTweetsLikedByUser(username);
		if (listOfTweet == null) {
			throw new TweetNotFoundException("No Tweet Found");
		}
		log.info("Getting all tweets liked by user finished.");
		return listOfTweet;
	}

	public List<LikeTweet> getLikedTweets() {
		// TODO Auto-generated method stub
		log.info("Getting all tweets liked by users started.");
		List<LikeTweet> listOfTweet = likeTweetRepository.findAll();
		if (listOfTweet == null) {
			throw new TweetNotFoundException("No Tweet Found");
		}
		log.info("Getting all tweets finished.");
		return listOfTweet;
	}
	
	public ReplyTweet ReplyTweetByUser(ReplyTweet replyTweetObj) {
		// TODO Auto-generated method stub
		log.info("Replying on  tweet started.");
		Tweet tweet = tweetRepository.findByUsernameAndId(replyTweetObj.getUser(), replyTweetObj.getTweetId());
		if (tweet == null) {
			throw new TweetNotFoundException("No Tweet Found");
		}
		log.info("Replying on  tweet finished.");
		return replyTweetRepository.save(replyTweetObj);
	}
	
	public List<ReplyTweet> getReliedTweets() {
		// TODO Auto-generated method stub
		log.info("Getting all tweets replied by users started.");
		List<ReplyTweet> listOfTweet = replyTweetRepository.findAll();
		if (listOfTweet == null) {
			throw new TweetNotFoundException("No Tweet Found");
		}
		log.info("Getting all tweet replies finished.");
		return listOfTweet;
	}

	public Optional<Tweet> getOneTweet(String id) {
		// TODO Auto-generated method stub
		log.info("Getting tweet started.");
		Optional<Tweet> tweet = tweetRepository.findById(id);
		if (tweet.isEmpty()) {
			throw new TweetNotFoundException("No Tweet Found");
		}
		log.info("Getting tweet finished.");
		return tweet;

	}

	public List<ReplyTweet> getRepliesById(String id) {
		// TODO Auto-generated method stub
		log.info("Getting tweet repliess by Id started.");
		id = "^"+id+"$";
		List<ReplyTweet> listOfTweet = replyTweetRepository.findByTweetId(id);
		log.info("Getting tweet repliess by Id finished.");
		return listOfTweet;
	}


}
