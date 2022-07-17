package com.tweetapp.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.tweetapp.model.User;

public interface UserRepository extends MongoRepository<User, String> {
	public User findByEmailAndPassword(String email, String password);
	public User findByUsernameAndPassword(String username, String password);
	public User findByUsername(String username);
    public User findByEmail(String email);
    
    @Query("{username : {$regex: ?0}}")   
    public List<User> findUserWithName(String username);
}
