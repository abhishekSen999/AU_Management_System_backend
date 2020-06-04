package com.au.web.controller.manager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.au.web.authorization.manager.ManagerAuthorizationInterface;

@RestController
public class ManagerOnboardLogController {
	
	@Autowired
	ManagerAuthorizationInterface user;

	@CrossOrigin
	@GetMapping("/manager/log")
	public Object getAllLog()
	{
		
		return user.getAuthorization("OnboardLogService","getAllLog", null);
						
	}
	
	
}
