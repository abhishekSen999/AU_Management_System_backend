package com.au.service;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.au.domain.Onboard;
import com.au.domain.OnboardLog;
import com.au.domain.Operation;
import com.au.repository.OnboardDAO;
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


	


	public int setLog(Operation  operation , long onb_id )
	{
		 
		
		OnboardLog onboardLog = new OnboardLog();
		String operator = getUser().getAuthenticatedUserEmail();
		onboardLog.setOnb_id(onb_id)
				.setOperation(operation)
				.setOperator(operator);
		
		
		if(operation == Operation.delete )
			return getOnboardLogDao().addLog(onboardLog);
		
		Onboard onboard =  getOnboardService().getById(onb_id);
		
		onboardLog.setEmp_id(onboard.getEmp_id())
				.setDem_id(onboard.getDem_id())
				.setStart_date(onboard.getStart_date())
				.setEta_of_completion(onboard.getEta_of_completion())
				.setOnboarding_status(onboard.getOnboarding_status())
				.setBgc_status(onboard.getBgc_status());
			
		
		return getOnboardLogDao().addLog(onboardLog);
		
		
	}
	
	
	public List<OnboardLog>getAllLog()
	{
		
		List<OnboardLog> logList = getOnboardLogDao().getAllLog();
		
		
		return logList;
	}
	
	public List<OnboardLog>getAllLogByOnboardId(long onb_id)
	{
		
		List<OnboardLog> logList = getOnboardLogDao().getAllLogByOnboardId(onb_id);
		
		
		return logList;
	}
	
	
	
	public List<OnboardLog>getAllLogByEmployeeId(long emp_id)
	{
		
		List<OnboardLog> logList = getOnboardLogDao().getAllLogByEmployeeId(emp_id);
		
		
		return logList;
	}
	
	public List<OnboardLog>getAllLogByDemandId(long dem_id)
	{
		
		List<OnboardLog> logList = getOnboardLogDao().getAllLogByDemandId(dem_id);
		
		
		return logList;
	}
	
	public List<OnboardLog>getAllLogByEmployeeIdAndDemandId(long emp_id , long dem_id)
	{
		
		List<OnboardLog> logList = getOnboardLogDao().getAllLogByEmployeeIdAndDemandId( emp_id , dem_id);
		
		
		return logList;
	}
	
	public List<OnboardLog> getAllLogByOperator(String operator)
	{
		
		List<OnboardLog> logList = getOnboardLogDao(). getAllLogByOperator(operator);
		
		
		return logList;
	}
	
	public List<OnboardLog> getAllLogByOperation(String operation)
	{
		
		
		List<OnboardLog> logList = getOnboardLogDao(). getAllLogByOperation(operation);
		
		
		return logList;
	}
	
	public List<OnboardLog> getAllLogBetweenTimestamp(Date timestamp1 ,Date timestamp2)
	{
		List<OnboardLog> logList = getOnboardLogDao(). getAllLogBetweenTimestamp(timestamp1, timestamp2);
		
		return logList;
	}
	
	
}
