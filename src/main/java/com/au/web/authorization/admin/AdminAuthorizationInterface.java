package com.au.web.authorization.admin;

public interface AdminAuthorizationInterface {
	
	
	public Object getAuthorization() ;
	
	public Object getAuthorization(String className , String functionName) ;  // the implementing class should use principal object to get authorization
	


	

}
