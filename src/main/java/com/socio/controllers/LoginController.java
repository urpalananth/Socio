/**
 * @author : Ananth Kumar C
 * @Creted on : 18-Oct-2012
 */
package com.socio.controllers;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.ldap.userdetails.LdapUserDetailsImpl;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.socio.beans.UserProfile;
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
			System.out.println("--> No user");
			return null;
		}
		String userId = user.getUsername();
		
		userRepo.findAll().size();
		
		return this.userRepo
			.findByName(userId);
	}
	
	private void validateUser(Principal principal) {
		String userId = principal.getName();
		this.userRepo
			.findByName(userId);
	}
}
