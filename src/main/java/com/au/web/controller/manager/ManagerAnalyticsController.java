package com.au.web.controller.manager;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.au.web.authorization.manager.ManagerAuthorizationInterface;
import com.au.web.security.AuthenticatedUserData;
import com.au.web.security.OAuthAuthenticatedUserDataInterface;
import com.au.web.security.tokenVerification.GoogleClientAPIWrapper;

@RestController
public class ManagerAnalyticsController {
	@Autowired
	OAuthAuthenticatedUserDataInterface userVerifier;
	
	@Autowired
	ManagerAuthorizationInterface user;
	
	@CrossOrigin
	@GetMapping("/manager/analytics/location")
	public Object getCountForAllLocation(@RequestHeader("Authorization") String idToken)
	{
		userVerifier.setIdToken(idToken);
		
		Object result;
		result = user.getAuthorization("DemandService", "getCountForAllLocation", null);
		return result;
		
		
	}
	
}
