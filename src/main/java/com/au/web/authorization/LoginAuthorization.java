package com.au.web.authorization;

import java.util.EnumMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.au.exception.customExceptions.UnauthorizedUserException;
import com.au.service.AuthorizationService;
import com.au.web.security.OAuthAuthenticatedUserDataInterface;

@Component
public class LoginAuthorization implements LoginAuthorizationInterface{

	@Autowired
	private OAuthAuthenticatedUserDataInterface user; 
	
	@Autowired
	private AuthorizationService authorizationService;
	
	
	

	@Override 
	public Map<UserDataKey, Object> getEmailAndAuthorizationLevel(String idToken)
	{
		
		Map<UserDataKey , Object> userData = new EnumMap<UserDataKey, Object>(UserDataKey.class); 
		
		String userEmail = user.getAuthenticatedUserEmail(idToken);
		AuthorizationLevel authorizationLevel = authorizationService.getUserAuthorizationLevel(userEmail);

		if(authorizationLevel == AuthorizationLevel.unauthorizedUser)
		{
			throw new UnauthorizedUserException(userEmail+" is not an authorized user, log out and try logging in from a different account.");
			
		}
		
		userData.put(UserDataKey.Email, userEmail);
		userData.put(UserDataKey.AuthorizationLevel,authorizationLevel);  
		
		return userData;
		
	}
	
	

	
}
