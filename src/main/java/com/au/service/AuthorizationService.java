package com.au.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.au.web.authorization.AuthorizationLevel;
@Component
public class AuthorizationService implements AuthorizationServiceInterface {

	private static Map<String, AuthorizationLevel> authorizationTable;  // stores (email,authorizationLevel) pair
	
	public AuthorizationService() {
		
		authorizationTable = new HashMap<String , AuthorizationLevel> ();
		
		
		//TODO: add access to table - hardcoding as of now 
		
		authorizationTable.put("abhishek.sen@accoliteindia.com", AuthorizationLevel.admin );
		authorizationTable.put("abhishek.sen999@gmail.com", AuthorizationLevel.manager );
		
		
//		System.out.println(authorizationTable);
	
	}
	
	
	
	/*
	 * Authorization levels:
	 * admin
	 * manager
	 * unauthorizedUser
	 */
	
	
	@Override
	public AuthorizationLevel getUserAuthorizationLevel(String email) { 
		
		
//		System.out.println("----------------------authorizationService Line 43-------------");
//		
//		System.out.println(authorizationTable.get(email));
//		
		// TODO Auto-generated method stub
		
		if(authorizationTable.containsKey(email))
			
			return authorizationTable.get(email);
		
		
		
		
		return AuthorizationLevel.unauthorizedUser;
	}
	
	

}
