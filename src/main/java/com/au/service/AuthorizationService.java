package com.au.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.au.web.authorization.AuthorizationLevel;
@Component
public class AuthorizationService {

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
	

	public AuthorizationLevel getUserAuthorizationLevel(String email) { 
		
		

		
		if(authorizationTable.containsKey(email))
			
			return authorizationTable.get(email);
		
		
		
		
		return AuthorizationLevel.unauthorizedUser;
	}
	
	

}
