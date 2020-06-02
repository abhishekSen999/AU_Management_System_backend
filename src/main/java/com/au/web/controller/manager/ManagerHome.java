package com.au.web.controller.manager;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.au.web.authorization.manager.ManagerAuthorizationInterface;
@RestController
public class ManagerHome {
	
	
	@Autowired
	ManagerAuthorizationInterface user;

	@CrossOrigin
	@GetMapping("/manager")
	public Object admin() {
		
		return user.getAuthorization(); // 
	}

		
		

}
