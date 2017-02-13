package com.socio.repo;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.socio.beans.Tweet;
import com.socio.beans.UserProfile;

public interface TweetRepository extends JpaRepository<Tweet, Long>{
	@Query(value="SELECT * FROM TWEET where tweeter_id in (SELECT u.id FROM user_profile u LEFT JOIN connection c WHERE c.followed_by_id = u.id AND c.follower_id = :userId)", nativeQuery=true)
	Collection<Tweet> findTweetsByUser(@Param("userId") Long userId);
}