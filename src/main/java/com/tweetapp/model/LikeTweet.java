package com.tweetapp.model;

import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@Document(collection = "liketweet")
public class LikeTweet {

	String tweetId;
	String user;
	String likedByUser;
}
