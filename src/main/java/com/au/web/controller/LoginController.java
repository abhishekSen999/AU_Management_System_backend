package com.au.web.controller;



import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;





import com.au.web.authorization.LoginAuthorizationInterface;
import com.au.web.authorization.UserDataKey;
import com.au.web.security.OAuthAuthenticatedUserDataInterface;



@RestController
public class LoginController {   //



	@Autowired
	LoginAuthorizationInterface user;
	

//	@Autowired
//	GoogleClientAPIWrapper verifier;
	
	@CrossOrigin
	@GetMapping("/user")
	public Object getAuthorizationLevel(@RequestHeader("Authorization") String idToken ) {
		
		
		
		Map<UserDataKey,Object> userData = user.getEmailAndAuthorizationLevel(idToken); 
		
		return  userData.get(UserDataKey.AuthorizationLevel);// get authorization for accessing these services
	}
	
	
	
}
