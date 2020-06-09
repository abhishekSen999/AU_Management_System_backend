//package com.au.web.controller.manager;
//
//import java.util.ArrayList;
//import java.util.Collection;
//import java.util.List;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.dao.EmptyResultDataAccessException;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.stereotype.Component;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.CrossOrigin;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.RestController;
//
//import com.au.web.authorization.manager.ManagerAuthorizationInterface;
//import com.au.web.security.OAuthAuthenticatedUserDataInterface;
//
//@RestController
//public class ManagerEmployeeController {
//
//	@Autowired
//	ManagerAuthorizationInterface user;
//	
//	@Autowired
//	OAuthAuthenticatedUserDataInterface userVerifier;
//
//	@CrossOrigin
//	@GetMapping("/employee")
//	public Object getEmployeeList() {
//		Object result = user.getAuthorization("EmployeeService", "getAll", null);
//		return result;
//	}
//
//	@CrossOrigin
//	@GetMapping("/employee/id={id}")
//	public Object getEmployeeById(@PathVariable long id) {
//
//		List<Object> parameterList = new ArrayList<Object>();
//		parameterList.add(id);
//		
//		Object result;
//		
//		try 
//		{
//			result = user.getAuthorization("EmployeeService", "getById", parameterList);
//		} 
//		catch (EmptyResultDataAccessException e) 
//		{
//			return new ResponseEntity<String>("requested resource not present check id", HttpStatus.BAD_REQUEST);
//		}
//			// TODO: handle exception
//		
//		return result;
//	}
//
//	@CrossOrigin
//	@GetMapping("/employee/company_email={email}")
//	public Object getEmployeeByCompanyEmail(@PathVariable String email) {
//
//		List<Object> parameterList = new ArrayList<Object>();
//		parameterList.add(email);
//		
//		Object result;
//		
//		try 
//		{
//			result = user.getAuthorization("EmployeeService", "getByCompanyEmail", parameterList);
//		} 
//		catch (EmptyResultDataAccessException e) 
//		{
//			return new ResponseEntity<String>("requested resource not present check email", HttpStatus.BAD_REQUEST);
//		}
//			// TODO: handle exception
//		
//		return result;
//	}
//
//	// TODO add furthur functionalities on employee table
//
//	@CrossOrigin
//	@GetMapping("/employee/personal_email={email}")
//	public Object getEmployeeByPersonalEmail(@PathVariable String email) {
//
//		List<Object> parameterList = new ArrayList<Object>();
//		parameterList.add(email);
//
//		Object result;
//		
//		try 
//		{
//			result = user.getAuthorization("EmployeeService", "getByPersonalEmail", parameterList);
//		} 
//		catch (EmptyResultDataAccessException e) 
//		{
//			return new ResponseEntity<String>("requested resource not present check email", HttpStatus.BAD_REQUEST);
//		}
//			// TODO: handle exception
//		
//		return result;
//
//	}
//
//	@CrossOrigin
//	@GetMapping("/employee/location={location}")
//	public Object getEmployeeByLocation(@PathVariable String location) {
//
//		List<Object> parameterList = new ArrayList<Object>();
//		parameterList.add(location);
//
//		Object result;
//		
//		try 
//		{
//			result = user.getAuthorization("EmployeeService", "getByLocation", parameterList);
//			if(((List)result).isEmpty()) 
//			{
//				throw new EmptyResultDataAccessException(1);
//			}
//		} 
//		catch (EmptyResultDataAccessException e) 
//		{
//			return new ResponseEntity<String>("requested resource not present check location", HttpStatus.BAD_REQUEST);
//		}
//			// TODO: handle exception
//		
//		return result;
//
//	}
//	
//	
//	
//	
//	
//	
//	
//	
//	
//	
//	
//	
//	
//	
//	
//	
//	
//
//}
