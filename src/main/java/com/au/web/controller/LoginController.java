package com.au.web.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.au.web.authorization.AuthorizationInterface;

@RestController
public class LoginController {
	
	@Autowired
	AuthorizationInterface user;

	@GetMapping("/")
	public String helloWorld() {
		return "unristricted endpoint";
	}
	
	@GetMapping("/user")
	public Object restrictedAdminEndpoint(Principal principal) {
		
		
		return user.getAuthorization("test class", "test Function");
	}
	
//	@GetMapping("/user")
//	public String restrictedUserEndpoint() {
//		return "you are loggedin as user";
//	}
	
}
