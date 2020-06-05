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
			TokenDomain token = restTemplate.getForObject("https://oauth2.googleapis.com/tokeninfo?id_token="+idToken, TokenDomain.class);
			

			return token.getEmail();
		}
		catch(Exception e)
		{
			return "unverified user";
		}
	}

}
