package com.au.web.security.tokenVerification;

import java.io.IOException;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
@Component
public class GoogleClientAPIWrapper {
	
	
	String varifiedEmail;
	
	
	
	public String getEmailFromIdToken( String idToken)
	{
		RestTemplate restTemplate = new RestTemplate();
		
		try{
			/*
			 * get request to the uri will return a json
			 * if provided token is not valid then a exception is throws by google server
			 * 
			 * if email_verified = true - the given email is verified by google
			 * 
			 */
			
			
			
			TokenDomain token = restTemplate.getForObject("https://oauth2.googleapis.com/tokeninfo?id_token="+idToken, TokenDomain.class);
			
			System.out.println(token);
			
			
			
			if(token.getEmail_verified().equalsIgnoreCase("true")) {
				System.out.println("here");
				return token.getEmail();
			}
			else {
				System.out.println("email not verified");
				return "unverified user";}
		}
		catch(Exception e)
		{
			return "unverified user";
		}
	}

}
