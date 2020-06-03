package com.au.web.authorization.manager;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;

import com.au.domain.Onboard;
import com.au.service.EmployeeService;
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
	
	
	@Override
	public Object getAuthorization() {  // returns authorization level for authorized user and logs out unauthorized user.
		
		
		
		AuthorizationLevel authorizationLevel =user.getAuthorizationLevel();
		
		if( authorizationLevel == AuthorizationLevel.unauthorizedUser )  // only manager and admin will get access
		{
			System.out.println(authorizationLevel);
			AutoLogout.autoLogout();
			return AutoLogout.autoLogout();
			
		}
		return authorizationLevel; 
		
	}

	@Override
	public Object getAuthorization(String className, String functionName ,  List<Object> parameterList) {

		AuthorizationLevel authorizationLevel =user.getAuthorizationLevel();
		
		if( authorizationLevel == AuthorizationLevel.unauthorizedUser )  // only manager and admin will get access
		{
			
			
			AutoLogout.autoLogout();
			//todo: logout user   
			return AutoLogout.autoLogout();
			
		}
		
		// todo: call services
		
	
		switch (className) {
		
		case "EmployeeService":
			
						switch(functionName) {
						
						case "getAll": return employeeService.getAll();
						
						case "getById": return employeeService.getById((long)parameterList.get(0));
						
						case "getByCompanyEmail": return employeeService.getByCompanyEmail((String)parameterList.get(0));
						
						case "getByPersonalEmail": return employeeService.getByPersonalEmail((String)parameterList.get(0));
						
						case "getByLocation": return employeeService.getByLocation((String)parameterList.get(0));
						
						default: break;
						
						}
		case "OnboardService":
						
						switch(functionName) {
						
						
						case "getAll": return onboardService.getAll();
						
						case "getById": return onboardService.getById((long)parameterList.get(0));
						
						case "getByEmployeeIdAndDemandId" : return onboardService.getByEmployeeIdAndDemandId((long)parameterList.get(0),(long)parameterList.get(1));
						
						case "add": return onboardService.add((Onboard)parameterList.get(0));
						
						case "update": return onboardService.update((Onboard)parameterList.get(0));
						
						case "delete": return onboardService.delete((long)parameterList.get(0));
						
						default : break;
						
						
						}
		
		
		
		
		
		
		
		
		}
		
		
		
		return authorizationLevel;
		
		
	}
}
