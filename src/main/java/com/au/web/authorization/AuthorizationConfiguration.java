package com.au.web.authorization;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.au.web.security.OAuthAuthenticatedUserDataFacade;

@Component
public class AuthorizationConfiguration implements AuthorizationInterface{

	@Autowired
	private OAuthAuthenticatedUserDataFacade user; 
	
	
	@Override
	public Object getAuthorization(String className, String functionName) {
		
		String userEmail = user.getAuthenticatedUserEmail();
		
		System.out.println(userEmail);
		
		
		
		if(!userEmail.equalsIgnoreCase("abhishek.sen999@gmail.com"))
		{
			return "Failed Authorization";
			
		}
		
		
		
		//TODO: implement services access
		return "passed Authorization"; 
	}

	
}
