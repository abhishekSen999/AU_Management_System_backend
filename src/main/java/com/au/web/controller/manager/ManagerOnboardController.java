package com.au.web.controller.manager;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.au.domain.Onboard;
import com.au.repository.OnboardDAO;
import com.au.service.OnboardService;
import com.au.web.authorization.manager.ManagerAuthorization;
import com.au.web.authorization.manager.ManagerAuthorizationInterface;

@RestController
public class ManagerOnboardController {

//	@Autowired
//	OnboardService onboardService;
	
	@Autowired
	ManagerAuthorizationInterface user;
	
	
	@CrossOrigin
	@GetMapping("/manager/onboard")
	public Object getAllOnboard() 
	{
		Object result = user.getAuthorization("OnboardService", "getAll", null) ;
		return result;
	}
	
	
	
	@CrossOrigin
	@GetMapping("/manager/onboard/id={onb_id}")
	public Object getOnboardById(@PathVariable long onb_id )
	{
		Object result;
		List<Object> parameterList = new ArrayList<Object>();
		parameterList.add(onb_id);
		
		try 
		{
			 result = user.getAuthorization("OnboardService", "getById", parameterList) ; // onboardService.getById(onb_id);
		}
		catch (EmptyResultDataAccessException e)
		{
			return new ResponseEntity<String>("requested resource not present check id", HttpStatus.BAD_REQUEST);
			
		}
		return result;
	}
	
	
	@CrossOrigin
	@PostMapping("/manager/onboard")
	public Object addOnboard(@RequestBody Onboard onboard)
	{
		List<Object> parameterList = new ArrayList<Object>();
		parameterList.add(onboard);
		//add service layer 
		return  user.getAuthorization("OnboardService", "add", parameterList);    //onboardService.add(onboard);
		
	}
	
	@CrossOrigin
	@PutMapping("/manager/onboard")
	public Object updateOnboard(@RequestBody Onboard onboard)
	{
		List<Object> parameterList = new ArrayList<Object>();
		parameterList.add(onboard);
		
		//add service layer 
		return  user.getAuthorization("OnboardService","update" ,parameterList );     //
		
	}
	
	@CrossOrigin
	@DeleteMapping("/manager/onboard/id={onb_id}")
	public Object deleteOnboard(@PathVariable long onb_id)
	{
		
		List<Object> parameterList = new ArrayList<Object>();
		parameterList.add(onb_id);
		
		//add service layer 
		return   user.getAuthorization("OnboardService", "delete", parameterList)  ;     //
		
	}
	
	
	
	
}
