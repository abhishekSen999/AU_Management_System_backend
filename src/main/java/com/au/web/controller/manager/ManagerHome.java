package com.au.web.controller.manager;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.au.web.authorization.manager.ManagerAuthorizationInterface;
@RestController
public class ManagerHome {
	
	
	@Autowired
	ManagerAuthorizationInterface user;


	@GetMapping("/manager")
	public Object admin() {
		
		return user.getAuthorization(); // 
	}
	
	@GetMapping("/manager/home")
	public Object adminHome() {
		
		return user.getAuthorization("test class" , "test Function" , null); // get authorization for accessing these services
	}
	
	
	//TODO : add furthur apis and functionalities
	
	@GetMapping("/manager/functionality")
	public Object login() {
		
		
		return user.getAuthorization("test_class", "test Function" , null); // get authorization for accessing these services
	}


}
