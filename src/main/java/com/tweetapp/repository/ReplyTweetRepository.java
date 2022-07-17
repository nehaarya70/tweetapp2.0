package com.tweetapp.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.tweetapp.model.ReplyTweet;

public interface ReplyTweetRepository extends MongoRepository<ReplyTweet, String> {

	@Query("{tweetId : {$regex: ?0}}")   
    public List<ReplyTweet> findByTweetId(String id);
}
