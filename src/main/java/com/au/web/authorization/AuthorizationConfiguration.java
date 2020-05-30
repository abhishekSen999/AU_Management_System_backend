package com.au.web.authorization;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.au.service.AuthorizationServiceInterface;
import com.au.web.security.OAuthAuthenticatedUserDataFacade;

@Component
public class AuthorizationConfiguration implements AuthorizationInterface{

	@Autowired
	private OAuthAuthenticatedUserDataFacade user; 
	
	@Autowired
	private AuthorizationServiceInterface authorizationService;
	
	@Override
	public Object getAuthorization(String className, String functionName) {
		
		String userEmail = user.getAuthenticatedUserEmail();
		
		System.out.println(userEmail);
		System.out.println(authorizationService);
		AuthorizationLevel authorizationLevel = authorizationService.getUserAuthorizationLevel(userEmail);
		
		if(authorizationLevel == AuthorizationLevel.unauthorizedUser)
		{
			return "Failed Authorization";
			
		}
		
		
		 
		//TODO: implement services access
		return authorizationLevel; 
	}

	
}
