package com.au.web.controller.admin;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


import com.au.web.authorization.admin.AdminAuthorizationInterface;

@RestController
public class AdminHome {
	
	@Autowired
	AdminAuthorizationInterface user;

	@CrossOrigin
	@GetMapping("/admin")
	public Object admin() {

		return user.getAuthorization(); // men
	}
	
	@CrossOrigin
	@GetMapping("/admin/home")
	public Object adminHome() {
		
		
		return user.getAuthorization("test class", "test Function"); // get authorization for accessing these services
	}
	
	
	//TODO : add furthur apis and functionalities
	@CrossOrigin
	@GetMapping("/admin/functionality")
	public Object login(Principal principal) {
		
		
		return user.getAuthorization("test_class", "test Function"); // get authorization for accessing these services
	}
	

}
