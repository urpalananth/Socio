/**
 * @author : Ananth Kumar C
 * @Creted on : 18-Oct-2012
 */
package com.socio.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.ldap.userdetails.LdapUserDetailsImpl;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.socio.beans.UserProfile;
import com.socio.exceptions.UserNotFoundException;
import com.socio.repo.UserProfileRepository;

@Controller
@RequestMapping("/principal")
public class LoginController {
    
	@Autowired
	private UserProfileRepository userRepo; 
	
	
	@RequestMapping(method=RequestMethod.GET)
	public @ResponseBody UserProfile getLoggedInUser(){
		LdapUserDetailsImpl user = (LdapUserDetailsImpl)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if(user == null){
			throw new UserNotFoundException(" user not logged in");
		}
		String userId = user.getUsername();
		return this.userRepo
			.findByName(userId);
	}
}
