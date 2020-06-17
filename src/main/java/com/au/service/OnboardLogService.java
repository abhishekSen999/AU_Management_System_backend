package com.au.service;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.au.domain.Onboard;
import com.au.domain.OnboardLog;
import com.au.domain.Operation;
import com.au.exception.customExceptions.FailedDatabaseLoggingException;
import com.au.exception.customExceptions.InvalidQueryDataException;
import com.au.repository.OnboardLogDAO;
import com.au.web.security.OAuthAuthenticatedUserDataInterface;

@Component
public class OnboardLogService {  
	
	@Autowired
	OnboardLogDAO onboardLogDao;
	
	
	
	
	@Autowired
	OAuthAuthenticatedUserDataInterface user;
	
	
	
	@Autowired
	OnboardService onboardService;
	
	
	
	
	 
	
	OnboardLogDAO getOnboardLogDao() {
		return onboardLogDao;
	}


	OAuthAuthenticatedUserDataInterface getUser() {
		return user;
	}


	OnboardService getOnboardService() {
		return onboardService;
	}


	


	public int setLog(Operation  operation , long onb_id , String userEmail)
	{
		 
		
		OnboardLog onboardLog = new OnboardLog();
		String operator = userEmail;
		onboardLog.setOnb_id(onb_id)
				.setOperation(operation)
				.setOperator(operator);
		
		
		if(operation == Operation.delete ) {
			int result = getOnboardLogDao().addLog(onboardLog);
			if(result == 0)
			{
				 throw new FailedDatabaseLoggingException("Error Occured During Logging of "+operation+" operation for Onboard Id: "+onb_id+" at "+ System.currentTimeMillis()  +  " Underlying cause is not known - repeat operation after some time.");
			}
			
			return result;
		}
		Onboard onboard =  getOnboardService().getById(onb_id);
		
		onboardLog.setEmp_id(onboard.getEmp_id())
				.setDem_id(onboard.getDem_id())
				.setStart_date(onboard.getStart_date())
				.setEta_of_completion(onboard.getEta_of_completion())
				.setOnboarding_status(onboard.getOnboarding_status())
				.setBgc_status(onboard.getBgc_status());
			
		
		int result = getOnboardLogDao().addLog(onboardLog);
		if(result == 0)
		{
			throw new FailedDatabaseLoggingException("Error Occured During Logging of "+operation+" operation for Onboard Id: "+onb_id+" at "+ System.currentTimeMillis()  +  " Underlying cause is not known - repeat operation after some time.");
		}
		
		return result;
		
		
	}
	
	
	public List<OnboardLog>getAllLog()
	{
		
		List<OnboardLog> logList = getOnboardLogDao().getAllLog();
		
		
		return logList;
	}
	
	public List<OnboardLog>getAllLogByOnboardId(long onb_id)
	{ 
		
		Optional<String> errorMessage = Optional.empty();

		if (onb_id <= 0) {
			errorMessage = Optional.of(" - Onboard id cannot be 0 - ");

		}

		errorMessage.ifPresent((message) -> {
			throw new InvalidQueryDataException(message);
		});

		
		List<OnboardLog> logList = getOnboardLogDao().getAllLogByOnboardId(onb_id);
		
		
		return logList;
	}
	
	
	
	public List<OnboardLog>getAllLogByEmployeeId(long emp_id)
	{
		Optional<String> errorMessage = Optional.empty();

		if (emp_id <= 0) {
			errorMessage = Optional.of(" - Employee id cannot be 0 - ");

		}

		errorMessage.ifPresent((message) -> {
			throw new InvalidQueryDataException(message);
		});

		
		List<OnboardLog> logList = getOnboardLogDao().getAllLogByEmployeeId(emp_id);
		
		
		return logList;
	}
	
	public List<OnboardLog>getAllLogByDemandId(long dem_id)
	{
		Optional<String> errorMessage = Optional.empty();

		if (dem_id <= 0) {
			errorMessage = Optional.of(" - demand id cannot be 0 - ");

		}

		errorMessage.ifPresent((message) -> {
			throw new InvalidQueryDataException(message);
		});

		
		List<OnboardLog> logList = getOnboardLogDao().getAllLogByDemandId(dem_id);
		
		
		return logList;
	}
	
	public List<OnboardLog>getAllLogByEmployeeIdAndDemandId(long emp_id , long dem_id)
	{
		Optional<String> errorMessage = Optional.empty();
		
		if (emp_id <= 0) {
			errorMessage = Optional.of(" - Employee Id cannot be 0 - ");
		}
		if (dem_id <= 0) {
			errorMessage = Optional.of(errorMessage.orElse("").concat(" - Demand Id cannot be 0 - "));
		}

		errorMessage.ifPresent((message) -> {
			throw new InvalidQueryDataException(message);
		});

		
		List<OnboardLog> logList = getOnboardLogDao().getAllLogByEmployeeIdAndDemandId( emp_id , dem_id);
		
		
		return logList;
	}
	
	public List<OnboardLog> getAllLogByOperator(String operator)
	{
		
		Optional<String> errorMessage = Optional.empty();

		if (operator == null) {
			errorMessage = Optional.of(" - Operator cannot be null  - ");

		}

		errorMessage.ifPresent((message) -> {
			throw new InvalidQueryDataException(message);
		});

		
		
		List<OnboardLog> logList = getOnboardLogDao(). getAllLogByOperator(operator);
		
		
		return logList;
	}
	
	public List<OnboardLog> getAllLogByOperation(String operation)
	{
		
		Optional<String> errorMessage = Optional.empty();

		if (operation == null) {
			errorMessage = Optional.of(" - Operation cannot be null  - ");

		}

		errorMessage.ifPresent((message) -> {
			throw new InvalidQueryDataException(message);
		});

		
		
		List<OnboardLog> logList = getOnboardLogDao(). getAllLogByOperation(operation);
		
		
		return logList;
	}
	
	public List<OnboardLog> getAllLogBetweenTimestamp(Date timestamp1 ,Date timestamp2)
	{
		
		Optional<String> errorMessage = Optional.empty();
		
		if (timestamp1 == null) {
			errorMessage = Optional.of(" - First date cannot be null - ");
		}
		if (timestamp2 == null) {
			errorMessage = Optional.of(errorMessage.orElse("").concat(" - Second date cannot be null - "));
		}

		errorMessage.ifPresent((message) -> {
			throw new InvalidQueryDataException(message);
		});


		
		
		List<OnboardLog> logList = getOnboardLogDao(). getAllLogBetweenTimestamp(timestamp1, timestamp2);
		
		return logList;
	}
	
	
}
