package com.socio.beans;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
public class UserProfile {
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	private String name;
	private String profilePhotoUrl;
	private String about;
	@OneToMany(mappedBy="follower")
	private Set<Connection> followers;
	@OneToMany(mappedBy="followedBy")
	private Set<Connection> followedBy;
	@OneToMany(mappedBy="tweeter")
	@JsonBackReference
	private Set<Tweet> tweets;
	public UserProfile() {}
	public UserProfile(String name, String profilePhotoUrl, String about, Set<Connection> followers) {
		this.name = name;
		this.profilePhotoUrl = profilePhotoUrl;
		this.followers = followers;
		this.about = about;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getProfilePhotoUrl() {
		return profilePhotoUrl;
	}
	public void setProfilePhotoUrl(String profilePhotoUrl) {
		this.profilePhotoUrl = profilePhotoUrl;
	}
	public Set<Connection> getFollower() {
		return followers != null ? followers : new HashSet<>();
	}
	public void setFollowers(Set<Connection> connections) {
		this.followers = connections;
	}
	public Set<Connection> getFollowedBy() {
		return followedBy != null ? followedBy : new HashSet<>();
	}
	public void setFollowedBy(Set<Connection> followedBy) {
		this.followedBy = followedBy;
	}
	public Set<Tweet> getTweets() {
		return tweets;
	}
	public void setTweets(Set<Tweet> tweets) {
		this.tweets = tweets;
	}
	public String getAbout() {
		return about;
	}
	public void setAbout(String about) {
		this.about = about;
	}
	public Set<Connection> getFollowers() {
		return followers;
	}
}
