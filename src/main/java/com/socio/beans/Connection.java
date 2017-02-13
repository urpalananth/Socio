package com.socio.beans;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Connection {
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	@JsonIgnore
	@ManyToOne			//  A is B's follower
	private UserProfile follower;
	@JsonIgnore
	@ManyToOne			//  B is followed by A
	private UserProfile followedBy;
	public Connection() {
	}
	public Connection(UserProfile follower, UserProfile followedBy) {
		this.follower = follower;
		this.followedBy = followedBy;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public UserProfile getFollower() {
		return follower;
	}
	public void setFollower(UserProfile follower) {
		this.follower = follower;
	}
	public UserProfile getFollowedBy() {
		return followedBy;
	}
	public void setFollowedBy(UserProfile followedBy) {
		this.followedBy = followedBy;
	}
	@Override
	public boolean equals(Object con) {
		return this.id.equals(((Connection)con).id);
	}
	@Override
	public int hashCode() {
		return this.id.hashCode();
	}
}
