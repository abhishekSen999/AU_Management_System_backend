package com.au.web.security;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.au.web.security.tokenVerification.GoogleClientAPIWrapper;

@Component
public class AuthenticatedUserData implements OAuthAuthenticatedUserDataInterface{ // class to get user data like email name from Principal object of the context

	
//	private String idToken;
//	
//
//	public void setIdToken(String idToken) {
//		this.idToken = idToken;
//	}

	@Autowired
	GoogleClientAPIWrapper verifyer;
	
	
	public AuthenticatedUserData() {
		
	}
	
	
	
	@Override
	public String getAuthenticatedUserEmail(String idToken) { // implemented using regex can be overriden later
		String email =  verifyer.getEmailFromIdToken(idToken);
		
		return email;
		
	}

	



	@Override
	public String getAuthenticatedUserName(String idToken) {
		// TODO Auto-generated method stub
		return null;
	}
	
	

	
	
	
	
	
	
}
