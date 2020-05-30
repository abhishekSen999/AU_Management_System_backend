package com.au.web.authorization.manager;

import java.util.List;

public interface ManagerAuthorizationInterface {
	
	
	public Object getAuthorization() ;
	
	public Object getAuthorization(String className , String functionName, List<Object> parameterList) ;  // the implementing class should use principal object to get authorization
	


	

}
