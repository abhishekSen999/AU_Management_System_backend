package com.au.web.security;

public interface OAuthAuthenticatedUserDataInterface {  

	public String getAuthenticatedUserEmail(String idToken);
	
	public String getAuthenticatedUserName(String idToken);
	
	
}
