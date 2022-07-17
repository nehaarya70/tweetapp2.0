package com.tweetapp.model;

import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@Document(collection = "replytweet")
public class ReplyTweet {

	String tweetId;
	String user;
	String replyByUser;
	String reply;
}
