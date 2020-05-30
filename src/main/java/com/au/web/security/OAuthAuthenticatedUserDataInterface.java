package com.au.web.security;

public interface OAuthAuthenticatedUserDataInterface { //interface to autowire user data from SecuirityContextHolder 

	public String getAuthenticatedUserEmail();
	
	public String getAuthenticatedUserName();
	
}
