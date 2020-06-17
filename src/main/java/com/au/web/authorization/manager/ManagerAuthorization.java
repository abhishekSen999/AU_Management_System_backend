package com.au.web.authorization.manager;

import java.sql.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.au.domain.Onboard;
import com.au.exception.customExceptions.UnauthorizedUserException;
import com.au.service.DemandService;
import com.au.service.EmployeeService;
import com.au.service.OnboardLogService;
import com.au.service.OnboardService;
import com.au.web.authorization.AuthorizationLevel;
import com.au.web.authorization.LoginAuthorizationInterface;
import com.au.web.authorization.UserDataKey;

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
	public Object getAuthorization(String idToken) {  // returns authorization level for authorized user and logs out unauthorized user.
		
		
		
		Map<UserDataKey,Object> userData =getUser().getEmailAndAuthorizationLevel(idToken);
		
		AuthorizationLevel authorizationLevel = Enum.valueOf(AuthorizationLevel.class, (userData.get(UserDataKey.AuthorizationLevel).toString()));
		
		String userEmail = userData.get(UserDataKey.Email).toString();
		
		System.out.println();
		if( authorizationLevel == AuthorizationLevel.unauthorizedUser )  // only manager and admin will get access
		{
			
			throw new UnauthorizedUserException(userEmail+" is not authorized to access this service.");
			
		}
		return authorizationLevel;  
		
	}

	@Override
	public Object getAuthorization(String idToken,String className, String functionName ,  List<Object> parameterList) {

		Map<UserDataKey,Object> userData =getUser().getEmailAndAuthorizationLevel(idToken);
		
		AuthorizationLevel authorizationLevel = Enum.valueOf(AuthorizationLevel.class, (userData.get(UserDataKey.AuthorizationLevel).toString()));
		
		String userEmail = userData.get(UserDataKey.Email).toString();
		
		if( authorizationLevel == AuthorizationLevel.unauthorizedUser )  // only manager and admin will get access
		{
			System.out.println(authorizationLevel);
			
			throw new UnauthorizedUserException(userEmail+" is not authorized to access this service.");
			
			
		}
		
		
		
	
		switch (className) {
		
//		case "EmployeeService":
//			
//						switch(functionName) {
//						
//						case "getAll": return getEmployeeService().getAll();
//						
//						case "getById": return getEmployeeService().getById((long)parameterList.get(0));
//						
//						case "getByCompanyEmail": return getEmployeeService().getByCompanyEmail((String)parameterList.get(0));
//						
//						case "getByPersonalEmail": return getEmployeeService().getByPersonalEmail((String)parameterList.get(0));
//						
//						case "getByLocation": return getEmployeeService().getByLocation((String)parameterList.get(0));
//						
//						default: break;
//						
//						}
//						break;
		case "OnboardService":
						
						switch(functionName) {
						
						
						case "getAll": return getOnboardService().getAll();
						
						case "getByStartDate": return getOnboardService().getByStartDate((Date)parameterList.get(0));
						
						case "getByEtaOfCompletion": return getOnboardService().getByEtaOfCompletion((Date)parameterList.get(0));
						
						case "getByOnboardingStatus": return getOnboardService().getByOnboardingStatus((String)parameterList.get(0));
						
						case "getByBgcStatus": return getOnboardService().getByBgcStatus((String)parameterList.get(0));
						
						case "getById": return getOnboardService().getById((long)parameterList.get(0));
						
						case "getByEmployeeIdAndDemandId" : return getOnboardService().getByEmployeeIdAndDemandId((long)parameterList.get(0),(long)parameterList.get(1));
						
						
						
						
						case "add": return getOnboardService().add((Onboard)parameterList.get(0) , userEmail);
						
						case "update": return getOnboardService().update((Onboard)parameterList.get(0) , userEmail );
						
						case "delete": return getOnboardService().delete((long)parameterList.get(0) , userEmail);
						
						default : break;
						
						
						}
						break;
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
						break;
						
		case "DemandService" : 
						switch (functionName) {
						
						case "getCountForAllLocation" : return getDemandService().getCountForAllLocation();
						
						}
						break;
		
		
		
		
		
		default: break;
		
		
		}
		
		
		
		return authorizationLevel;
		
		
	}
}
