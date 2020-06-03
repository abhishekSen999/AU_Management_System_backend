package com.au.web.controller;



import java.sql.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.au.domain.Demand;
import com.au.domain.ProjectAllocation;
import com.au.repository.DemandDAO;
import com.au.repository.EmployeeDAO;
import com.au.repository.EmployeeSkillsetDAO;
import com.au.repository.ProjectAllocationDAO;
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
	ProjectAllocationDAO projectAllocationDao;
	
	@Autowired
	DemandDAO demandDao;
	
	@Autowired
	LoginAuthorizationInterface user;

	@CrossOrigin
	@GetMapping("/test/{emp_id}")
	public Object unristrictedEndpoint1(@PathVariable long emp_id) {
		
		
		return projectAllocationDao.getLastProjectAllocationOfEmployeeWithId(emp_id);
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
