package com.au.web.controller.manager;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.au.web.authorization.manager.ManagerAuthorizationInterface;
import com.au.web.security.AuthenticatedUserData;

@RestController
public class ManagerOnboardLogController {

	@Autowired 
	ManagerAuthorizationInterface user;
	
	@Autowired
	AuthenticatedUserData userVerifier;
	
	//making class testable
	ManagerAuthorizationInterface getUser()
	{
		return user;
	}
	
	AuthenticatedUserData getUserVerifier()
	{
		return userVerifier;
	}
	

	@CrossOrigin
	@GetMapping("/manager/log")
	public Object getAllLog(@RequestHeader("Authorization") String idToken) {
		
		
		
		getUserVerifier().setIdToken(idToken);

		return getUser().getAuthorization("OnboardLogService", "getAllLog", null);

	}
	
	
	
	

	@CrossOrigin
	@GetMapping("/manager/log/emp_id={emp_id}")
	public Object getAllLogByEmployeeId(@RequestHeader("Authorization") String idToken,@PathVariable long emp_id) {
		
		getUserVerifier().setIdToken(idToken);
		
		
		List<Object> parameterList = new ArrayList<Object>();
		parameterList.add(emp_id);
		Object result;

		try {
			result = getUser().getAuthorization("OnboardLogService", "getAllLogByEmployeeId", parameterList);
			if (((List) result).isEmpty()) {
				throw new EmptyResultDataAccessException(1);
			}
		} catch (EmptyResultDataAccessException e) {

			return new ResponseEntity<String>("requested resource not found", HttpStatus.BAD_REQUEST);

		}

		return result;

	}
	
	
	@CrossOrigin
	@GetMapping("/manager/log/onb_id={onb_id}")
	public Object getAllLogByOnboardId(@RequestHeader("Authorization") String idToken,@PathVariable long onb_id) {
		
		getUserVerifier().setIdToken(idToken);
		
		
		List<Object> parameterList = new ArrayList<Object>();
		parameterList.add(onb_id);
		Object result;

		try {
			result = getUser().getAuthorization("OnboardLogService", "getAllLogByOnboardId", parameterList);
			if (((List) result).isEmpty()) {
				throw new EmptyResultDataAccessException(1);
			}
		} catch (EmptyResultDataAccessException e) {

			return new ResponseEntity<String>("requested resource not found", HttpStatus.BAD_REQUEST);

		}

		return result;

	}
	
	
	
	
	
	
	
	
	
	
	

	@CrossOrigin
	@GetMapping("/manager/log/dem_id={dem_id}")
	public Object getAllLogByDemandId(@RequestHeader("Authorization") String idToken,@PathVariable long dem_id) {
		
		getUserVerifier().setIdToken(idToken);
		
		List<Object> parameterList = new ArrayList<Object>();
		parameterList.add(dem_id);
		Object result;

		try {
			result = getUser().getAuthorization("OnboardLogService", "getAllLogByDemandId", parameterList);
			if (((List) result).isEmpty()) {
				throw new EmptyResultDataAccessException(1);
			}
		} catch (EmptyResultDataAccessException e) {

			return new ResponseEntity<String>("requested resource not found", HttpStatus.BAD_REQUEST);

		}

		return result;

	}
	
	
	
	
	@CrossOrigin
	@GetMapping("/manager/log/both/emp_id={emp_id}&dem_id={dem_id}")
	public Object getAllLogByEmployeeIdAndDemandId(@RequestHeader("Authorization") String idToken,@PathVariable long emp_id, @PathVariable long dem_id)
	{	
		getUserVerifier().setIdToken(idToken);
		
		List<Object> parameterList = new ArrayList<Object>();
		parameterList.add(emp_id);
		parameterList.add(dem_id);
		Object result;

		try {
			result = getUser().getAuthorization("OnboardLogService", "getAllLogByEmployeeIdAndDemandId", parameterList);
			if (((List) result).isEmpty()) {
				throw new EmptyResultDataAccessException(1);
			}
		} catch (EmptyResultDataAccessException e) {

			return new ResponseEntity<String>("requested resource not found", HttpStatus.BAD_REQUEST);

		}
		return result;
	}
	
	
	
	
	

	@CrossOrigin
	@GetMapping("/manager/log/operator={operator}")
	public Object getAllLogByOperator(@RequestHeader("Authorization") String idToken,@PathVariable String operator) {
		
		
		getUserVerifier().setIdToken(idToken);
		
		List<Object> parameterList = new ArrayList<Object>();
		parameterList.add(operator);

		Object result;

		try {
			result = getUser().getAuthorization("OnboardLogService", "getAllLogByOperator", parameterList);
			if (((List) result).isEmpty()) {
				throw new EmptyResultDataAccessException(1);
			}
		} catch (EmptyResultDataAccessException e) {

			return new ResponseEntity<String>("requested resource not found", HttpStatus.BAD_REQUEST);

		}
		return result;

	}
	
	
	
	
	
	
	
	@CrossOrigin
	@GetMapping("/manager/log/operation={operation}")
	public Object getAllLogByOperation(@RequestHeader("Authorization") String idToken,@PathVariable String operation) {
		
		getUserVerifier().setIdToken(idToken);
		
		List<Object> parameterList = new ArrayList<Object>();
		parameterList.add(operation);

		Object result; 

		try {
			result = getUser().getAuthorization("OnboardLogService", "getAllLogByOperation", parameterList);
			if (((List) result).isEmpty()) {
				throw new EmptyResultDataAccessException(1);
			}
		} catch (EmptyResultDataAccessException e) {

			return new ResponseEntity<String>("requested resource not found", HttpStatus.BAD_REQUEST);

		}
		return result;

	}
	
	 
	
	
	
	
	
	@CrossOrigin
	@GetMapping("/manager/log/date1={timestamp1}&date2={timestamp2}")
	public Object getAllLogBetweenTimestamp(@RequestHeader("Authorization") String idToken,@PathVariable Date timestamp1, @PathVariable Date timestamp2) {
		
		getUserVerifier().setIdToken(idToken);
		
		List<Object> parameterList = new ArrayList<Object>();
		parameterList.add(timestamp1);
		parameterList.add(timestamp2);
		Object result;

		try {
			result = getUser().getAuthorization("OnboardLogService", "getAllLogBetweenTimestamp", parameterList);
			if (((List) result).isEmpty()) {
				throw new EmptyResultDataAccessException(1);
			}
		} catch (EmptyResultDataAccessException e) {

			return new ResponseEntity<String>("requested resource not found", HttpStatus.BAD_REQUEST);

		}
		return result;
	}
		
	

}
