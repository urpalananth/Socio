package com.socio.controllers;

import org.springframework.web.bind.annotation.RequestMapping;

//@Controller
public class MainController {
	
	@RequestMapping("/")
	public String home(){
		return "index";
	}
	
	@RequestMapping("/followers")
	public String followers(){
		return "users";
	}
}
