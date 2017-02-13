/**
 * @author : Ananth Kumar C
 * @Creted on : 18-Oct-2012
 */
package com.socio.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.socio.beans.Connection;
import com.socio.beans.UserProfile;
import com.socio.repo.ConnectionRepository;
import com.socio.repo.UserProfileRepository;

@Controller
@RequestMapping("/connection")
public class ConnectionsController {
    /**
	 * 
	 */
	@Autowired
	private ConnectionRepository connectionRepo;
	@Autowired
	private UserProfileRepository userRepo; 
	
	@RequestMapping(method = RequestMethod.POST)
	public @ResponseBody Connection create(@Param("from") String from, @Param("to") Long to) {
		UserProfile fromUser = userRepo.findByName(from);
		UserProfile toUser = userRepo.getOne(to);
		Connection connection = connectionRepo.save(new Connection(fromUser, toUser));
	    return connection;
	}
}
