package com.socio.controllers;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.ldap.userdetails.LdapUserDetailsImpl;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.socio.beans.Tweet;
import com.socio.exceptions.UserNotFoundException;
import com.socio.exceptions.UserUnauthorizedException;
import com.socio.repo.TweetRepository;
import com.socio.repo.UserProfileRepository;

@Controller
@RequestMapping("{userId}/tweets")
public class TweetsController {

	private UserProfileRepository userRepo;
	
	private TweetRepository tweetRepo;
	
	@Autowired
	public TweetsController(UserProfileRepository userRepo, TweetRepository tweetRepo) {
		super();
		this.userRepo = userRepo;
		this.tweetRepo = tweetRepo;
	}

	@RequestMapping(method = RequestMethod.GET)
	public @ResponseBody Collection<Tweet> getTweets(@PathVariable String userId){
		this.validateUser(userId);
		Collection<Tweet> tweets = this.tweetRepo.findTweetsByUser(userRepo.findByName(userId).getId());
		tweets.addAll(userRepo.findByName(userId).getTweets());
		return tweets;
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public @ResponseBody Tweet create(@PathVariable String userId, @Param("message") String message) {
		this.validateUser(userId);
		return tweetRepo.save(new Tweet(this.userRepo.findByName(userId), message));
	}
	/**
	 * 
	 * @param userId
	 */
	private void validateUser(String userId) {
		LdapUserDetailsImpl user = (LdapUserDetailsImpl)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if(!userId.equals(user.getUsername())){
			throw new UserUnauthorizedException(userId);
		}
		if(this.userRepo.findByName(userId) == null) {
			throw new UserNotFoundException(userId) ;
		}
	}
}
