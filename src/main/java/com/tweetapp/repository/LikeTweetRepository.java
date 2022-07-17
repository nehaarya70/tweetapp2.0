package com.tweetapp.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.tweetapp.model.LikeTweet;

public interface LikeTweetRepository extends MongoRepository<LikeTweet, String> {

	@Query("{likedByUser : {$regex: ?0}}")   
    public List<LikeTweet> findTweetsLikedByUser(String username);
	
	@Query(value="{'tweetId' : ?0, 'likedByUser' : ?1}", delete = true)
	public LikeTweet deleteByIdAndUsername (String tweetId, String likedByUser);
	
}
