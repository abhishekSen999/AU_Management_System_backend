package com.au.web.authorization;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;

import com.au.service.AuthorizationService;
import com.au.web.security.OAuthAuthenticatedUserDataInterface;

@Component
public class LoginAuthorization implements LoginAuthorizationInterface{

	@Autowired
	private OAuthAuthenticatedUserDataInterface user; 
	
	@Autowired
	private AuthorizationService authorizationService;
	
	
	@Override
	public Object getAuthorization() {
		
		
		
		AuthorizationLevel authorizationLevel = getAuthorizationLevel();
		
		if(authorizationLevel == AuthorizationLevel.unauthorizedUser)
		{
			return AutoLogout.autoLogout();
			
		}
		
		
		 
		//TODO: implement services access
		return authorizationLevel; 
	}

	@Override 
	public AuthorizationLevel getAuthorizationLevel()
	{
		String userEmail = user.getAuthenticatedUserEmail();
		AuthorizationLevel authorizationLevel = authorizationService.getUserAuthorizationLevel(userEmail);
		return authorizationLevel;
		
	}
	
	

	
}
