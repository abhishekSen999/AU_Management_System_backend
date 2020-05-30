package com.au.web.security;

public interface OAuthAuthenticatedUserDataFacade { //interface to autowire user data from SecuirityContextHolder 

	public String getAuthenticatedUserEmail();
	
	public String getAuthenticatedUserName();
	
}
