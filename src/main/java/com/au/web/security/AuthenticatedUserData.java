package com.au.web.security;

import java.io.IOException;
import java.security.Principal;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class AuthenticatedUserData implements OAuthAuthenticatedUserDataInterface{ // class to get user data like email name from Principal object of the context

	public String emailPattern = "[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}";
	
	
	
	public AuthenticatedUserData() {
		
	}
	
	@Override
	public String getAuthenticatedUserEmail() { // implemented using regex can be overriden later
		
		
		String email = getEmail(SecurityContextHolder.getContext().getAuthentication().getPrincipal());
		
		return email;
		
	}

	@Override
	public String getAuthenticatedUserName() {
		// TODO Auto-generated method stub
		return null;
	}
	
	

	private String getEmail(Object principal)  //implemented with regex ca later be overriden
	{
		String email = emailPatternMatcher( emailPattern , principal.toString() );
		
		return email;
	}
	
	public String emailPatternMatcher(String patternString , String matcherString )
	{
		
		Pattern pattern = Pattern.compile(patternString , Pattern.CASE_INSENSITIVE);
		Matcher matcher = pattern.matcher(matcherString);
		
		String email ; 		
		if(matcher.find()) {
			email = matcher.group(0);
			
			
//			email = email.replaceAll("email=" , "" );
//			email = email.replaceAll( "}*" , "" );
//			
			return email;
		}
		return null;
	}
	
	
	
	
	
	
}
