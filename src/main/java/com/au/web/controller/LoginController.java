package com.au.web.controller;



import java.sql.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.au.domain.Demand;
import com.au.domain.ProjectAllocation;
import com.au.repository.DemandDAO;
import com.au.repository.EmployeeDAO;
import com.au.repository.EmployeeSkillsetDAO;
import com.au.repository.ProjectAllocationDAO;
import com.au.repository.SkillDAO;
import com.au.web.authorization.LoginAuthorizationInterface;
import com.au.web.security.AuthenticatedUserData;
import com.au.web.security.OAuthAuthenticatedUserDataInterface;
import com.au.web.security.tokenVerification.GoogleClientAPIWrapper;



@RestController
public class LoginController {   //
	
	
	
	
	
	@Autowired
	LoginAuthorizationInterface user;
	
	@Autowired
	AuthenticatedUserData userVerifier;

//	@Autowired
//	GoogleClientAPIWrapper verifier;
	
	@CrossOrigin
	@GetMapping("/user")
	public Object login(@RequestHeader("Authorization") String idToken ) {
		
		
		userVerifier.setIdToken(idToken);
		
		
		return user.getAuthorization(); // get authorization for accessing these services
	}
	
	
	
}
