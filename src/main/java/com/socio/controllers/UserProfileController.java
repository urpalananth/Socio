/**
 * @author : Ananth Kumar C
 * @Creted on : 18-Oct-2012
 */
package com.socio.controllers;

import java.util.Collection;

import org.hibernate.boot.model.naming.IllegalIdentifierException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.ldap.userdetails.LdapUserDetailsImpl;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.socio.beans.UserProfile;
import com.socio.repo.UserProfileRepository;

@Controller
@RequestMapping("/user")
public class UserProfileController {
    
	@Autowired
	private UserProfileRepository userRepo; 
	
	@RequestMapping(method=RequestMethod.GET)
    public  @ResponseBody Collection<UserProfile> findFriends(@RequestParam(value="follow") String mode) throws Exception {
		LdapUserDetailsImpl user = (LdapUserDetailsImpl)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		
		if(mode.equals("all")){
			return userRepo.findByIdNotIn(userRepo.findByName(user.getUsername()).getId());
		}else if(mode.equals("followers")){
			return userRepo.findFollowing(userRepo.findByName(user.getUsername()).getId());
		}else if(mode.equals("following")) {
			return userRepo.findFollowers(userRepo.findByName(user.getUsername()).getId());
		}else{
			throw new IllegalIdentifierException("No valid parameter");
		}
    }
}
