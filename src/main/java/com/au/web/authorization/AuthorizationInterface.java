package com.au.web.authorization;

import org.springframework.stereotype.Component;

@Component
public interface AuthorizationInterface {
	
	
	public Object getAuthorization(String className , String functionName) ;  // the implementing class should use principal object to get authorization
	

}
