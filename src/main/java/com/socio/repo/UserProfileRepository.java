package com.socio.repo;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.socio.beans.UserProfile;

public interface UserProfileRepository extends JpaRepository<UserProfile, Long>{
	UserProfile findByName(String name);
	
	//@Query(value="select * from user_profile u1 where u1.id not in (SELECT u.id FROM user_profile u left join CONNECTION c where c.follower_id = :id and c.followed_by_id = u.id) and u1.id != :id", nativeQuery = true)
	@Query(value="SELECT * FROM user_profile u1 WHERE u1.id NOT IN (SELECT u.id FROM user_profile u LEFT JOIN connection c WHERE c.follower_id = :id AND c.followed_by_id = u.id) AND u1.id != :id", nativeQuery = true)
	Collection<UserProfile>findByIdNotIn(@Param("id") Long id);
	//my followers
	@Query(value="SELECT * FROM user_profile u LEFT JOIN connection c WHERE c.follower_id = :id AND c.followed_by_id = u.id", nativeQuery = true)
	Collection<UserProfile>findFollowers(@Param("id") Long id);
	//I am following
	@Query(value="SELECT * FROM user_profile u LEFT JOIN connection c WHERE c.followed_by_id = :id AND c.follower_id = u.id", nativeQuery = true)
	Collection<UserProfile>findFollowing(@Param("id") Long id);
}
