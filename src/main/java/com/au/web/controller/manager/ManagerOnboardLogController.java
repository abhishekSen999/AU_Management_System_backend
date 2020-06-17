package com.au.web.controller.manager;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.au.web.authorization.manager.ManagerAuthorizationInterface;


@RestController
public class ManagerOnboardLogController {

	@Autowired
	ManagerAuthorizationInterface user;

	// making class testable
	ManagerAuthorizationInterface getUser() {
		return user;
	}

	@CrossOrigin
	@GetMapping("/manager/log")
	public Object getAllLog(@RequestHeader("Authorization") String idToken) {

		return getUser().getAuthorization(idToken, "OnboardLogService", "getAllLog", null);

	}

	@CrossOrigin
	@GetMapping("/manager/log/emp_id={emp_id}")
	public Object getAllLogByEmployeeId(@RequestHeader("Authorization") String idToken, @PathVariable long emp_id) {

		List<Object> parameterList = new ArrayList<Object>();
		parameterList.add(emp_id);
		Object result;

		result = getUser().getAuthorization(idToken, "OnboardLogService", "getAllLogByEmployeeId", parameterList);

		return result;

	}

	@CrossOrigin
	@GetMapping("/manager/log/onb_id={onb_id}")
	public Object getAllLogByOnboardId(@RequestHeader("Authorization") String idToken, @PathVariable long onb_id) {

		List<Object> parameterList = new ArrayList<Object>();
		parameterList.add(onb_id);
		Object result;

		result = getUser().getAuthorization(idToken, "OnboardLogService", "getAllLogByOnboardId", parameterList);

		return result;

	}

	@CrossOrigin
	@GetMapping("/manager/log/dem_id={dem_id}")
	public Object getAllLogByDemandId(@RequestHeader("Authorization") String idToken, @PathVariable long dem_id) {

		List<Object> parameterList = new ArrayList<Object>();
		parameterList.add(dem_id);
		Object result;

		result = getUser().getAuthorization(idToken, "OnboardLogService", "getAllLogByDemandId", parameterList);

		return result;

	}

	@CrossOrigin
	@GetMapping("/manager/log/both/emp_id={emp_id}&dem_id={dem_id}")
	public Object getAllLogByEmployeeIdAndDemandId(@RequestHeader("Authorization") String idToken,
			@PathVariable long emp_id, @PathVariable long dem_id) {

		List<Object> parameterList = new ArrayList<Object>();
		parameterList.add(emp_id);
		parameterList.add(dem_id);
		Object result;

		result = getUser().getAuthorization(idToken, "OnboardLogService", "getAllLogByEmployeeIdAndDemandId",
				parameterList);

		return result;
	}

	@CrossOrigin
	@GetMapping("/manager/log/operator={operator}")
	public Object getAllLogByOperator(@RequestHeader("Authorization") String idToken, @PathVariable String operator) {

		List<Object> parameterList = new ArrayList<Object>();
		parameterList.add(operator);

		Object result;

		result = getUser().getAuthorization(idToken, "OnboardLogService", "getAllLogByOperator", parameterList);
		return result;

	}

	@CrossOrigin
	@GetMapping("/manager/log/operation={operation}")
	public Object getAllLogByOperation(@RequestHeader("Authorization") String idToken, @PathVariable String operation) {

		List<Object> parameterList = new ArrayList<Object>();
		parameterList.add(operation);

		Object result;

		result = getUser().getAuthorization(idToken, "OnboardLogService", "getAllLogByOperation", parameterList);
		return result;

	}

	@CrossOrigin
	@GetMapping("/manager/log/date1={timestamp1}&date2={timestamp2}")
	public Object getAllLogBetweenTimestamp(@RequestHeader("Authorization") String idToken,
			@PathVariable Date timestamp1, @PathVariable Date timestamp2) {

		List<Object> parameterList = new ArrayList<Object>();
		parameterList.add(timestamp1);
		parameterList.add(timestamp2);
		Object result;

		result = getUser().getAuthorization(idToken, "OnboardLogService", "getAllLogBetweenTimestamp", parameterList);
		return result;
	}

}
