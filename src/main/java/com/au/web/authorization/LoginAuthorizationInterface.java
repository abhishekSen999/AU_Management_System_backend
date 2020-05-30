package com.au.web.authorization;

import org.springframework.stereotype.Component;

@Component
public interface LoginAuthorizationInterface {
	
	
	public Object getAuthorization() ;  // the implementing class should use principal object to get authorization
	public AuthorizationLevel getAuthorizationLevel();

}
