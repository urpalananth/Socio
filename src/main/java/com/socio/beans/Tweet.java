package com.socio.beans;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
public class Tweet {
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long Id;
	@ManyToOne
	 @JsonManagedReference
	private UserProfile tweeter;
	private String message;
	public Tweet() {
	}
	public Tweet(UserProfile tweeter, String message) {
		this.tweeter = tweeter;
		this.message = message;
	}
	public Long getId() {
		return Id;
	}
	public void setId(Long id) {
		Id = id;
	}
	public UserProfile getTweeter() {
		return tweeter;
	}
	public void setTweeter(UserProfile tweeter) {
		this.tweeter = tweeter;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
}
