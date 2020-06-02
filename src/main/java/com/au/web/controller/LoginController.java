package com.au.web.controller;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.au.domain.Demand;
import com.au.repository.DemandDAO;
import com.au.repository.EmployeeDAO;
import com.au.repository.EmployeeSkillsetDAO;
import com.au.repository.SkillDAO;
import com.au.web.authorization.LoginAuthorizationInterface;



@RestController
public class LoginController {   //
	
	
	
	//remove this
	@Autowired
	SkillDAO skillDao;
	
	@Autowired
	EmployeeSkillsetDAO employeeSkillsetDAO;
	
	@Autowired
	DemandDAO demandDao;
	
	@Autowired
	LoginAuthorizationInterface user;

	@CrossOrigin
	@GetMapping("/test/{id}")
	public Object unristrictedEndpoint1(@PathVariable long id) {
		
		
		return demandDao.getById(1);
//		return null;
				
	}
	
	@CrossOrigin
	@GetMapping("/test")
	public Object unristrictedEndpoint() {
		
		
		return demandDao.getAll();
//		return null;
				
	}
	
	
	
	
	
	@CrossOrigin
	@GetMapping("/user")
	public Object login() {
		
		
		return user.getAuthorization(); // get authorization for accessing these services
	}
	
	
	
}
