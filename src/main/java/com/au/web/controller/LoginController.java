package com.au.web.controller;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.au.web.authorization.LoginAuthorizationInterface;

@RestController
public class LoginController {   //
	
	@Autowired
	LoginAuthorizationInterface user;

	@CrossOrigin
	@GetMapping("/")
	public String unristrictedEndpoint() {
		return "Server is Up";
	}
	@CrossOrigin
	@GetMapping("/user")
	public Object login() {
		
		
		return user.getAuthorization(); // get authorization for accessing these services
	}
	
	
	
}
