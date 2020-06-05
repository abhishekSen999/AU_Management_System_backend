package com.au.web.controller.manager;

import java.sql.Date;
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
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.au.domain.Onboard;
import com.au.repository.OnboardDAO;
import com.au.service.OnboardService;
import com.au.web.authorization.manager.ManagerAuthorization;
import com.au.web.authorization.manager.ManagerAuthorizationInterface;
import com.au.web.security.AuthenticatedUserData;

@RestController
public class ManagerOnboardController { 

//	@Autowired
//	OnboardService onboardService;
	
	@Autowired
	AuthenticatedUserData userVerifier;
	
	@Autowired
	ManagerAuthorizationInterface user;
	
	
	@CrossOrigin
	@GetMapping("/manager/onboard")
	public Object getAllOnboard(@RequestHeader("Authorization") String idToken) 
	{
		userVerifier.setIdToken(idToken);
		
		Object result = user.getAuthorization("OnboardService", "getAll", null) ;
		return result;
	}
	
	
	@CrossOrigin
	@GetMapping("/manager/onboard/start_date={start_date}")
	public Object getAllOnboardByStartDate(@RequestHeader("Authorization") String idToken , @PathVariable Date start_date)
	{
		List<Object> parameterList = new ArrayList<Object>();
		parameterList.add(start_date);
		Object result;
		
		userVerifier.setIdToken(idToken);
		
		try 
		{
			result = user.getAuthorization("OnboardService", "getByStartDate",parameterList);
			if(((List)result).isEmpty()) 
			{
				throw new EmptyResultDataAccessException(1);
			}
		} 
		catch (EmptyResultDataAccessException e) {
			
			return new ResponseEntity<String>("requested resource not present check start date", HttpStatus.BAD_REQUEST);
				
		}
		
		
		return result;
	}
	
	@CrossOrigin
	@GetMapping("/manager/onboard/eta_of_completion={eta_of_completion}")
	public Object getAllOnboardByEtaOfCompletion(@RequestHeader("Authorization") String idToken,@PathVariable Date eta_of_completion)
	{
		userVerifier.setIdToken(idToken);
		
		
		List<Object> parameterList = new ArrayList<Object>();
		parameterList.add(eta_of_completion);
		Object result;
		
		try  
		{
			result = user.getAuthorization("OnboardService", "getByEtaOfCompletion",parameterList);
			if(((List)result).isEmpty()) 
			{
				throw new EmptyResultDataAccessException(1);
			}
		} 
		catch (EmptyResultDataAccessException e) {
			
			return new ResponseEntity<String>("requested resource not present check eta of completion", HttpStatus.BAD_REQUEST);
				
		}
		
		return result;
	}
	
	@CrossOrigin
	@GetMapping("/manager/onboard/onboarding_status={onboarding_status}")
	public Object getAllOnboardByOnboardingStatus(@RequestHeader("Authorization") String idToken,@PathVariable String onboarding_status)
	{
		userVerifier.setIdToken(idToken);
		
		List<Object> parameterList = new ArrayList<Object>();
		parameterList.add(onboarding_status);
		Object result;
		
		try 
		{
			result = user.getAuthorization("OnboardService", "getByOnboardingStatus",parameterList);
			if(((List)result).isEmpty()) 
			{
				throw new EmptyResultDataAccessException(1);
			}
		} 
		catch (EmptyResultDataAccessException e) {
			
			return new ResponseEntity<String>("requested resource not present check  onboarding status", HttpStatus.BAD_REQUEST);
				// TODO Auto-generated catch block	
		}
		
		return result;
	}
	
	
	
	
	
	
	
	
	
	
	@CrossOrigin
	@GetMapping("/manager/onboard/bgc_status={bgc_status}")
	public Object getAllOnboardByBgcStatus(@RequestHeader("Authorization") String idToken,@PathVariable String bgc_status)
	{
		userVerifier.setIdToken(idToken);
		
		List<Object> parameterList = new ArrayList<Object>();
		parameterList.add(bgc_status);
		Object result;
		
		try 
		{
			result = user.getAuthorization("OnboardService", "getByBgcStatus" , parameterList);
			if(((List)result).isEmpty()) 
			{
				throw new EmptyResultDataAccessException(1);
			}
		} 
		catch (EmptyResultDataAccessException e) {
			
			return new ResponseEntity<String>("requested resource not present check bgc status", HttpStatus.BAD_REQUEST);
				// TODO Auto-generated catch block	
		}
		
		return result;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	@CrossOrigin
	@GetMapping("/manager/onboard/id={onb_id}")
	public Object getOnboardById(@RequestHeader("Authorization") String idToken,@PathVariable long onb_id )
	{
		
		userVerifier.setIdToken(idToken);
		
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
	@GetMapping("/manager/onboard/emp_id={emp_id}&dem_id={dem_id}")
	public Object getOnboardByEmployeeIdAndDemandId(@RequestHeader("Authorization") String idToken,@PathVariable long emp_id, @PathVariable long dem_id)
	{
		
		userVerifier.setIdToken(idToken);
		
		Object result;
		List<Object> parameterList = new ArrayList<Object>();
		parameterList.add(emp_id);
		parameterList.add(dem_id);
		
		try 
		{
			 result = user.getAuthorization("OnboardService", "getByEmployeeIdAndDemandId", parameterList) ; // onboardService.getById(onb_id);
		}
		catch (EmptyResultDataAccessException e)
		{
			return new ResponseEntity<String>("requested resource not present check  both id", HttpStatus.BAD_REQUEST);
			
		}
		return result;
		
		
	}
	
	
	@CrossOrigin
	@PostMapping("/manager/onboard")
	public Object addOnboard(@RequestHeader("Authorization") String idToken,@RequestBody Onboard onboard)
	{
		userVerifier.setIdToken(idToken);
		
		
		
		List<Object> parameterList = new ArrayList<Object>();
		parameterList.add(onboard);
		
		return  user.getAuthorization("OnboardService", "add", parameterList);    //onboardService.add(onboard);
		
	}
	
	@CrossOrigin
	@PutMapping("/manager/onboard")
	public Object updateOnboard(@RequestHeader("Authorization") String idToken,@RequestBody Onboard onboard)
	{
		userVerifier.setIdToken(idToken);
		
		
		List<Object> parameterList = new ArrayList<Object>();
		parameterList.add(onboard);
		
		//add service layer 
		return  user.getAuthorization("OnboardService","update" ,parameterList );     //
		
	}
	
	@CrossOrigin
	@DeleteMapping("/manager/onboard/id={onb_id}")
	public Object deleteOnboard(@RequestHeader("Authorization") String idToken,@PathVariable long onb_id)
	{
		userVerifier.setIdToken(idToken);
		
		List<Object> parameterList = new ArrayList<Object>();
		parameterList.add(onb_id);
		
		//add service layer 
		return   user.getAuthorization("OnboardService", "delete", parameterList)  ;     //
		
	}
	
	
	
	
	
	
}
