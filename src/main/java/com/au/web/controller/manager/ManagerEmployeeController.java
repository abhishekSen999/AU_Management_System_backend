package com.au.web.controller.manager;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.au.web.authorization.manager.ManagerAuthorizationInterface;

@Controller
public class ManagerEmployeeController {
	
	@Autowired
	ManagerAuthorizationInterface user;
	
	
	@CrossOrigin
	@GetMapping("/employee")
	public Object getEmployeeList()
	{
		Object result = user.getAuthorization("EmployeeService" , "getAll" , null );
		return result;
	}
	
	@CrossOrigin
	@GetMapping("/employee/id/{id}")
	public Object getEmployee(@PathVariable long  id)
	{
		
		List<Object> parameterList = new ArrayList<Object>();
		parameterList.add(id);
		
		return user.getAuthorization("EmployeeService" , "getById", parameterList );
	}
	@CrossOrigin
	@GetMapping("/employee/company_email/{email}")
	public Object getEmployee(@PathVariable String email)
	{
		
		List<Object> parameterList = new ArrayList<Object>();
		parameterList.add(email);
		
		return user.getAuthorization("EmployeeService" , "getByCompanyEmail", parameterList );
	}
	
	//TODO add furthur functionalities on employee table
	
	
	
	
	
	
	

}
