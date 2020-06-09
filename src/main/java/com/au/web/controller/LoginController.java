package com.au.web.controller;



import java.sql.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;





import com.au.web.authorization.LoginAuthorizationInterface;
import com.au.web.security.AuthenticatedUserData;
import com.au.web.security.OAuthAuthenticatedUserDataInterface;



@RestController
public class LoginController {   //
	
	
	
	
	
	@Autowired
	LoginAuthorizationInterface user;
	
	@Autowired
	OAuthAuthenticatedUserDataInterface userVerifier;

//	@Autowired
//	GoogleClientAPIWrapper verifier;
	
	@CrossOrigin
	@GetMapping("/user")
	public Object login(@RequestHeader("Authorization") String idToken ) {
		
		
		userVerifier.setIdToken(idToken);
		
		
		return user.getAuthorization(); // get authorization for accessing these services
	}
	
	
	
}
