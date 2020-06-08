package com.au.web.authorization.manager;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;

import com.au.domain.Onboard;
import com.au.service.DemandService;
import com.au.service.EmployeeService;
import com.au.service.OnboardLogService;
import com.au.service.OnboardService;
import com.au.web.authorization.AuthorizationLevel;
import com.au.web.authorization.AutoLogout;
import com.au.web.authorization.LoginAuthorizationInterface;

@Component
public class ManagerAuthorization implements ManagerAuthorizationInterface{

	@Autowired
	LoginAuthorizationInterface user;
	
	
	
	
	@Autowired
	EmployeeService employeeService;
	
	@Autowired
	OnboardService onboardService;
	
	@Autowired
	OnboardLogService onboardLogService;
	
	@Autowired
	DemandService demandService;
	
	
	//making class testable
	LoginAuthorizationInterface getUser()
	{
		return user;
	}
	
	
	//making class testable
	OnboardService getOnboardService() {
		return onboardService;
	}
	//making class testable
	EmployeeService getEmployeeService() {
		return employeeService;
	}
	//making class testable
	OnboardLogService getOnboardLogService() {
		return onboardLogService;
	}
	//making class testable
	DemandService getDemandService() {
		return demandService;
	}
	
	
	
	@Override
	public Object getAuthorization() {  // returns authorization level for authorized user and logs out unauthorized user.
		
		
		
		AuthorizationLevel authorizationLevel =getUser().getAuthorizationLevel();
		System.out.println(authorizationLevel);
		if( authorizationLevel == AuthorizationLevel.unauthorizedUser )  // only manager and admin will get access
		{
			
			AutoLogout.autoLogout();
			return AutoLogout.autoLogout();
			
		}
		return authorizationLevel;  
		
	}

	@Override
	public Object getAuthorization(String className, String functionName ,  List<Object> parameterList) {

		AuthorizationLevel authorizationLevel =getUser().getAuthorizationLevel();
		
		if( authorizationLevel == AuthorizationLevel.unauthorizedUser )  // only manager and admin will get access
		{
			System.out.println(authorizationLevel);
			
			AutoLogout.autoLogout();
			  
			return AutoLogout.autoLogout();
			
		}
		
		
		
	
		switch (className) {
		
		case "EmployeeService":
			
						switch(functionName) {
						
						case "getAll": return getEmployeeService().getAll();
						
						case "getById": return getEmployeeService().getById((long)parameterList.get(0));
						
						case "getByCompanyEmail": return getEmployeeService().getByCompanyEmail((String)parameterList.get(0));
						
						case "getByPersonalEmail": return getEmployeeService().getByPersonalEmail((String)parameterList.get(0));
						
						case "getByLocation": return getEmployeeService().getByLocation((String)parameterList.get(0));
						
						default: break;
						
						}
		case "OnboardService":
						
						switch(functionName) {
						
						
						case "getAll": return getOnboardService().getAll();
						
						case "getByStartDate": return getOnboardService().getByStartDate((Date)parameterList.get(0));
						
						case "getByEtaOfCompletion": return getOnboardService().getByEtaOfCompletion((Date)parameterList.get(0));
						
						case "getByOnboardingStatus": return getOnboardService().getByOnboardingStatus((String)parameterList.get(0));
						
						case "getByBgcStatus": return getOnboardService().getByBgcStatus((String)parameterList.get(0));
						
						case "getById": return getOnboardService().getById((long)parameterList.get(0));
						
						case "getByEmployeeIdAndDemandId" : return getOnboardService().getByEmployeeIdAndDemandId((long)parameterList.get(0),(long)parameterList.get(1));
						
						
						
						
						case "add": return getOnboardService().add((Onboard)parameterList.get(0));
						
						case "update": return getOnboardService().update((Onboard)parameterList.get(0));
						
						case "delete": return getOnboardService().delete((long)parameterList.get(0));
						
						default : break;
						
						
						}
		case "OnboardLogService" :
						switch(functionName) {
						
						case "getAllLog": return getOnboardLogService().getAllLog();
						
						case "getAllLogByEmployeeId" : return getOnboardLogService().getAllLogByEmployeeId((long)parameterList.get(0));
						
						case "getAllLogByDemandId" : return getOnboardLogService().getAllLogByDemandId((long)parameterList.get(0));
						
						case "getAllLogByEmployeeIdAndDemandId" : return getOnboardLogService().getAllLogByEmployeeIdAndDemandId((long)parameterList.get(0), (long)parameterList.get(1));
						
						case "getAllLogByOperator" : return getOnboardLogService().getAllLogByOperator((String)parameterList.get(0));
						
						
						case "getAllLogByOperation" : return getOnboardLogService().getAllLogByOperation((String)parameterList.get(0));
						
						
						case "getAllLogBetweenTimestamp" : return getOnboardLogService().getAllLogBetweenTimestamp((Date)parameterList.get(0),(Date)parameterList.get(1));
						
						case "getAllLogByOnboardId" : return getOnboardLogService().getAllLogByOnboardId((long)parameterList.get(0));
						
						}
		case "DemandService" : 
						switch (functionName) {
						
						case "getCountForAllLocation" : return getDemandService().getCountForAllLocation();
						
						}
		
		
		
		
		
		default: break;
		
		
		}
		
		
		
		return authorizationLevel;
		
		
	}
}
